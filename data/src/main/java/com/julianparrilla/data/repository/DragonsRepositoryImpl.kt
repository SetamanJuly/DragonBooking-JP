package com.julianparrilla.data.repository

import arrow.core.Either
import arrow.core.right
import com.julianparrilla.data.datasource.cache.DragonsCacheDataSource
import com.julianparrilla.data.datasource.remote.DragonsRemoteDataSource
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.mapper.toDomain
import com.julianparrilla.data.utils.toHashMap
import com.julianparrilla.data.utils.toQuery
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.model.NetworkError
import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class DragonsRepositoryImpl(
    private val dragonsRemoteDataSource: DragonsRemoteDataSource,
    private val dragonsCacheDataSource: DragonsCacheDataSource
) : DragonsRepository {

    override fun getAllDragons(currency: CurrencyDataState): Flow<Return<DragonsDataState>> = flow {
        emitAll(
            object : NetworkBoundResource<DragonsModel, DragonsDataState>(
                IO,
                apiCall = {
                    dragonsRemoteDataSource.allDragons(currency.toHashMap())
                },
                cacheCall = {
                    dragonsCacheDataSource.getAllCharacters()
                }
            ) {
                override suspend fun handleNetworkSuccess(response: DragonsModel): Return<DragonsDataState>? {
                    return response.toDomain().right()
                }

                override suspend fun handleCacheSuccess(response: DragonsModel?): Return<DragonsDataState>? {
                    return response?.toDomain()?.right()
                }

                override suspend fun updateCache(networkObject: DragonsModel) {
                    dragonsCacheDataSource.insertCharacters(resultsEntity = networkObject)
                }
            }.result
        )
    }

    override fun getFilteredData(
        params: DragonFilterParams,
        currency: CurrencyDataState
    ): Flow<Return<DragonsDataState>> = flow {
        emitAll(
            object : NetworkBoundResource<DragonsModel, DragonsDataState>(
                IO,
                cacheCall = {
                    dragonsCacheDataSource.getFilteredDragons(params.toQuery())
                }
            ) {
                override suspend fun handleCacheSuccess(response: DragonsModel?): Return<DragonsDataState>? {
                    return response?.toDomain()?.right()
                }
            }.result
        )
    }

    override fun getOriginAndDestinations(): Flow<Return<Pair<List<String>, List<String>>>> = flow {
        emitAll(
            object :
                NetworkBoundResource<Pair<List<String>, List<String>>, Pair<List<String>, List<String>>>(
                    IO,
                    cacheCall = {
                        dragonsCacheDataSource.getOriginAndDestinations()
                    }
                ) {
                override suspend fun handleCacheSuccess(response: Pair<List<String>, List<String>>?): Either<NetworkError, Pair<List<String>, List<String>>>? {
                    return response?.right()
                }
            }.result
        )
    }

    override fun getAvailableCoins(): Flow<Return<List<String>>> = flow {
        emitAll(
            object : NetworkBoundResource<List<String>, List<String>>(
                IO,
                cacheCall = {
                    dragonsCacheDataSource.getAvailableCoins()
                }
            ) {
                override suspend fun handleCacheSuccess(response: List<String>?): Either<NetworkError, List<String>>? {
                    return response?.right()
                }
            }.result
        )
    }
}
