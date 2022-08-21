package com.julianparrilla.domain.utils

import arrow.core.Either
import arrow.core.computations.EitherEffect
import arrow.core.computations.either
import com.julianparrilla.domain.model.NetworkError
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@Suppress("FunctionName")
interface WithScope : CoroutineScope {
    val io: CoroutineContext
    val jobs: MutableList<Job>

    fun <A, B> launchIOSafe(
        f: suspend () -> Either<A, B>,
        error: suspend (A) -> Unit = {},
        success: suspend (B) -> Unit = {}
    ): Job =
        launch {
            IO { f() }.fold(
                ifLeft = { Main { error(it) } },
                ifRight = { Main { success(it) } },
            )
        }

    fun <A> launchIO(
        f: suspend () -> A,
        success: (A) -> Unit = {}
    ): Job =
        launch {
            success(IO { f() })
        }

    fun <A> launchIO(
        f: suspend () -> A
    ): Job =
        launch { IO { f() } }

    suspend fun <T> IO(block: suspend CoroutineScope.() -> T): T =
        withContext(io) { block() }

    suspend fun <T> AsynckIO(block: suspend CoroutineScope.() -> T): Deferred<T> =
        async(io) { block() }

    suspend fun <T> Main(block: suspend CoroutineScope.() -> T): T =
        withContext(coroutineContext) { block() }

    fun <A> launchMain(
        f: suspend () -> A
    ): Job =
        launch { f() }

    fun <A, B> flowIO(
        f: suspend () -> Flow<Either<A, B>>,
        error: suspend (A) -> Unit = {},
        success: suspend (B) -> Unit = {}
    ): Job =
        launch {
            IO { f() }.collect {
                Main {
                    it.fold(
                        { error(it) },
                        { success(it) }
                    )
                }
            }
        }.also {
            jobs.add(it)
        }

    fun <A> IoEither(
        onSuccess: (A) -> Unit = {},
        onError: (NetworkError) -> Unit = {},
        f: suspend EitherEffect<NetworkError, *>.() -> A,
    ): Job =
        launchIO {
            either<NetworkError, A> {
                f(this)
            }.fold({ onError(it) }, { onSuccess(it) })
        }

    fun <A> EitherEffect<NetworkError, *>.bindAsync(f: suspend () -> Either<NetworkError, A>): Deferred<A> =
        async(io) { f().bind() }

    suspend fun <A> EitherEffect<NetworkError, *>.then(f: suspend () -> Either<NetworkError, A>): Either<NetworkError, A> =
        f()

    fun cancel(): Unit =
        coroutineContext.cancelChildren()

    fun pause() {
        jobs.onEach { it.cancel() }
    }

    fun <A> MainEither(
        onSuccess: (A) -> Unit = {},
        onError: (NetworkError) -> Unit = {},
        f: suspend EitherEffect<NetworkError, *>.() -> A,
    ) {
        launchMain {
            either<NetworkError, A> {
                f(this)
            }.fold({ onError(it) }, { onSuccess(it) })
        }
    }
}
