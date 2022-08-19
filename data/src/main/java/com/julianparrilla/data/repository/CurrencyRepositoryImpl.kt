package com.julianparrilla.data.repository

import arrow.core.Either
import arrow.core.right
import com.julianparrilla.data.datasource.remote.CurrencyRemoteDataSource
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.mapper.toDomain
import com.julianparrilla.domain.utils.Return
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.NetworkError
import com.julianparrilla.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class CurrencyRepositoryImpl(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {

    override fun getCurrencyConversion(
        from: String,
        to: String
    ): Flow<Either<NetworkError, CurrencyDataState>> = flow {
        emitAll(
            object : NetworkBoundResource<CurrencyModel, CurrencyModel, CurrencyDataState>(
                IO,
                apiCall = {
                    currencyRemoteDataSource.currencyChange("", "")
                }
            ) {
                override suspend fun handleNetworkSuccess(response: CurrencyModel): Return<CurrencyDataState>? {
                    return response.toDomain().right()
                }

                override suspend fun handleCacheSuccess(response: CurrencyModel?): Return<CurrencyDataState>? {
                    return response?.toDomain()?.right()
                }
            }.result
        )
    }

}
