package com.julianparrilla.data.repository

import arrow.core.Either
import arrow.core.left
import com.julianparrilla.data.utils.Return
import com.julianparrilla.domain.model.*
import com.julianparrilla.domain.utils.CACHE_TIMEOUT
import com.julianparrilla.domain.utils.NETWORK_TIMEOUT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

abstract class NetworkBoundResource<NetworkObj, CacheObj, ViewState>(
    private val dispatcher: CoroutineDispatcher,
    private val apiCall: (suspend () -> Return<NetworkObj>)? = null,
    val cacheCall: (suspend () -> Return<CacheObj>?)? = null
) {
    val result: Flow<Return<ViewState>> = flow {
        cacheCall?.let {
            emitAll(safeCacheCall())
        }
        apiCall?.let {
            emitAll(safeAPICall())
        }
    }

    private suspend fun safeAPICall() = flow {
        withContext(dispatcher) {
            kotlin.runCatching {
                withTimeout(NETWORK_TIMEOUT) {
                    apiCall?.invoke()?.let {
                        it.fold(
                            ifRight = { data ->
                                updateCache(data)
                                handleNetworkSuccess(data)?.let {
                                    emit(it)
                                }
                            },
                            ifLeft = {
                                emit(it.left())
                            }
                        )
                    }
                }
            }.onFailure {
                when (it) {
                    is TimeoutCancellationException -> {
                        emit(NetworkTimeOutError.left())
                    }
                    is IOException -> {
                        emit(NetworkConnectionError.left())
                    }
                    is HttpException -> {
                        emit(NetworkParsedError(convertErrorBody(it)).left())
                    }
                    else -> {
                        emit(NetworkUnknownError.left())
                    }
                }
            }
        }
    }

    private suspend fun safeCacheCall() = flow {
        withContext(dispatcher) {
            kotlin.runCatching {
                withTimeout(CACHE_TIMEOUT) {
                    cacheCall?.invoke()?.fold(
                        ifRight = {
                            handleCacheSuccess(it)?.let {
                                emit(it)
                            }
                        },
                        ifLeft = {
                            emit(it.left())
                        }
                    )
                }
            }.onFailure {
                when (it) {
                    is TimeoutCancellationException -> {
                        emit(NetworkTimeOutError.left())
                    }
                    else -> {
                        emit(NetworkUnknownError.left())
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): String =
        throwable.response()?.errorBody()?.string() ?: NetworkUnknownError.error

    open suspend fun handleNetworkSuccess(response: NetworkObj):
            Either<NetworkError, ViewState>? {
        return null
    }

    open suspend fun handleCacheSuccess(response: CacheObj?):
            Either<NetworkError, ViewState>? {
        return null
    }

    open suspend fun updateCache(networkObject: NetworkObj) {
        // Waiting for override
    }
}
