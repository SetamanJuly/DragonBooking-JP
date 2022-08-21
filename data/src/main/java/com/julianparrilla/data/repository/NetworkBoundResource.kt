@file:OptIn(ExperimentalCoroutinesApi::class)

package com.julianparrilla.data.repository

import arrow.core.Either
import arrow.core.left
import com.julianparrilla.domain.model.*
import com.julianparrilla.domain.utils.CACHE_TIMEOUT
import com.julianparrilla.domain.utils.NETWORK_TIMEOUT
import com.julianparrilla.domain.utils.Return
import java.io.IOException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<NetworkObj, ViewState>(
    private val dispatcher: CoroutineDispatcher,
    private val apiCall: (suspend () -> NetworkObj)? = null,
    private val cacheCall: (suspend () -> NetworkObj?)? = null
) {
    val result: Flow<Return<ViewState>> = flow {
        cacheCall?.let {
            emitAll(safeCacheCall())
        }
        apiCall?.let {
            emitAll(safeAPICall())
        }
    }

    private suspend fun safeAPICall() = channelFlow {
        withContext(dispatcher) {
            kotlin.runCatching {
                withTimeout(NETWORK_TIMEOUT) {
                    apiCall?.invoke()?.let {
                        updateCache(it)
                        handleNetworkSuccess(it)?.let {
                            send(it)
                        }
                    }
                }
            }.onFailure {
                when (it) {
                    is TimeoutCancellationException -> {
                        send(NetworkTimeOutError.left())
                    }
                    is IOException -> {
                        send(NetworkConnectionError.left())
                    }
                    is HttpException -> {
                        send(NetworkParsedError(convertErrorBody(it)).left())
                    }
                    else -> {
                        send(NetworkUnknownError.left())
                    }
                }
            }
        }
    }

    private suspend fun safeCacheCall() = channelFlow {
        withContext(dispatcher) {
            kotlin.runCatching {
                withTimeout(CACHE_TIMEOUT) {
                    cacheCall?.invoke().let {
                        handleCacheSuccess(it)?.let {
                            send(it)
                        }
                    }
                }
            }.onFailure {
                when (it) {
                    is TimeoutCancellationException -> {
                        send(NetworkTimeOutError.left())
                    }
                    else -> {
                        send(NetworkUnknownError.left())
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

    open suspend fun handleCacheSuccess(response: NetworkObj?):
        Either<NetworkError, ViewState>? {
            return null
        }

    open suspend fun updateCache(networkObject: NetworkObj) {
        // Waiting for override
    }
}
