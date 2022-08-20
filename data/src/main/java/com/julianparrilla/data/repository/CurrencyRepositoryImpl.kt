package com.julianparrilla.data.repository

import arrow.core.Either
import arrow.core.right
import com.julianparrilla.data.datasource.remote.CurrencyRemoteDataSource
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.mapper.toDomain
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.CurrencyItemState
import com.julianparrilla.domain.model.NetworkError
import com.julianparrilla.domain.repository.CurrencyRepository
import com.julianparrilla.domain.utils.Return
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
        from: List<String>,
        to: String
    ): Flow<Either<NetworkError, CurrencyDataState>> = flow {
        emitAll(
            object :
                NetworkBoundResource<List<CurrencyModel>, CurrencyDataState>(
                    IO,
                    apiCall = {
                        currencyRemoteDataSource.currencyChange(from, to)
                    }
                ) {
                override suspend fun handleNetworkSuccess(response: List<CurrencyModel>): Return<CurrencyDataState> {
                    val hashItems =  hashMapOf<String, CurrencyItemState>()
                    response.mapIndexed { index, currencyModel ->
                        hashItems.put(from[index], currencyModel.toDomain())
                    }
                    return CurrencyDataState(
                        items = hashItems
                    ).right()
                }
            }.result
        )
    }

}
