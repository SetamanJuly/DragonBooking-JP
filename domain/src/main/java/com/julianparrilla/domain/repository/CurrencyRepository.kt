package com.julianparrilla.domain.repository

import arrow.core.Either
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.NetworkError
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getCurrencyConversion(from: List<String>, to: String): Flow<Either<NetworkError, CurrencyDataState>>

}
