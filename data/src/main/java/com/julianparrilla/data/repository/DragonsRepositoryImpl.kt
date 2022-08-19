package com.julianparrilla.data.repository

import arrow.core.Either
import arrow.core.right
import com.julianparrilla.data.datasource.cache.DragonsCacheDataSource
import com.julianparrilla.data.datasource.remote.DragonsRemoteDataSource
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.mapper.toDomain
import com.julianparrilla.data.utils.toQuery
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.utils.Return
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.model.NetworkError
import com.julianparrilla.domain.repository.DragonsRepository
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

    override fun getCharacters(): Flow<Return<DragonsDataState>> = flow {
        emitAll(
            object : NetworkBoundResource<DragonsModel, DragonsModel, DragonsDataState>(
                IO,
                apiCall = {
                    dragonsRemoteDataSource.characters()
                },
                cacheCall = {
                    dragonsCacheDataSource.getAllCharacters()
                }
            ) {
                override suspend fun handleNetworkSuccess(response: DragonsModel): Return<DragonsDataState>? {
                    dragonsCacheDataSource.insertCharacters(resultsEntity = response)
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

    override fun getFilteredData(params: DragonFilterParams): Flow<Return<DragonsDataState>> = flow {
        emitAll(
            object : NetworkBoundResource<DragonsModel, DragonsModel, DragonsDataState>(
                IO,
                cacheCall = {
                    dragonsCacheDataSource.getFilteredDragons(params.toQuery())
                }
            ) {

                override suspend fun handleCacheSuccess(response: DragonsModel?): Return<DragonsDataState>? {
                    return response?.toDomain()?.right()
                }

                override suspend fun updateCache(networkObject: DragonsModel) {
                    dragonsCacheDataSource.insertCharacters(resultsEntity = networkObject)
                }

            }.result
        )
    }

    override fun getOriginAndDestinations(): Flow<Return<Pair<List<String>, List<String>>>> = flow {
        emitAll(
            object : NetworkBoundResource<Pair<List<String>, List<String>>, Pair<List<String>, List<String>>, Pair<List<String>, List<String>>>(
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

    //override fun getCharacters(): Flow<DataState<CharactersListViewState>> = flow {
   //    emitAll(
   //        object : NetworkBoundResource<DataCharacterEntity, DataCharacterEntity, CharactersListViewState>(
   //            IO,
   //            apiCall = { marvelRemoteDataSource.characters() },
   //            cacheCall = { marvelCacheDataSource.getAllCharacters() }
   //        ) {
   //            override suspend fun handleCacheSuccess(response: DataCharacterEntity?): DataState<CharactersListViewState>? {
   //                return response?.let {
   //                    DataState.SUCCESS(
   //                        CharactersListViewState(
   //                            CharacterFields(
   //                                response.data.results.toPresentation()
   //                            )
   //                        )
   //                    )
   //                }
   //            }

   //            override suspend fun handleNetworkSuccess(response: DataCharacterEntity): DataState<CharactersListViewState>? {
   //                return if (response.code == HttpURLConnection.HTTP_OK) {
   //                    marvelCacheDataSource.insertCharacters(resultsEntity = response)
   //                    DataState.SUCCESS(
   //                        CharactersListViewState(
   //                            CharacterFields(
   //                                response.data.results.toPresentation()
   //                            )
   //                        )
   //                    )
   //                } else {
   //                    emit(buildDialogError(response.status))
   //                    DataState.ERROR(
   //                        StateMessage(
   //                            message = response.status,
   //                            messageType = MessageType.ERROR,
   //                            uiComponentType = UIComponentType.DIALOG
   //                        )
   //                    )
   //                }
   //            }

   //            override suspend fun updateCache(networkObject: DataCharacterEntity) {
   //                marvelCacheDataSource.insertCharacters(resultsEntity = networkObject)
   //            }
   //        }.result
   //    )
   //}

    //override fun getSingleCharacter(idCharacter: Int): Flow<DataState<CharactersListViewState>> = flow {
    //    emitAll(
    //        object : NetworkBoundResource<DataCharacterEntity, DataCharacterEntity, CharactersListViewState>(
    //            IO,
    //            apiCall = { marvelRemoteDataSource.singleCharacter(idCharacter) }
    //        ) {
    //            override suspend fun handleNetworkSuccess(response: DataCharacterEntity): DataState<CharactersListViewState>? {
    //                return if (response.code == HttpURLConnection.HTTP_OK) {
    //                    DataState.SUCCESS(
    //                        CharactersListViewState(
    //                            CharacterFields(
    //                                response.data.results.toPresentation()
    //                            )
    //                        )
    //                    )
    //                } else {
    //                    DataState.ERROR(
    //                        StateMessage(
    //                            message = response.status,
    //                            messageType = MessageType.ERROR,
    //                            uiComponentType = UIComponentType.DIALOG
    //                        )
    //                    )
    //                }
    //            }
    //        }.result
    //    )
    //}

}
