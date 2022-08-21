package com.julianparrilla.domain.model

sealed class NetworkError(open val error: String)
object NetworkTimeOutError : NetworkError("Network timeout")
object NetworkConnectionError : NetworkError("Network error")
object NetworkCacheTimeOutError : NetworkError("Network cache timeout")
object NetworkUnknownError : NetworkError("Unknown error")
data class NetworkParsedError(override val error: String) : NetworkError(error)
