package com.julianparrilla.remote.source

import com.julianparrilla.data.datasource.remote.DragonsRemoteDataSource
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.utils.Return
import com.julianparrilla.remote.model.toData
import com.julianparrilla.remote.service.DragonsApiService

class DragonsRemoteDataSourceImpl(
    private val dragonsApiService: DragonsApiService
) : DragonsRemoteDataSource {

    override suspend fun characters(): DragonsModel =
        dragonsApiService.getDragonList().toData()

    override suspend fun singleCharacter(idCharacter: Int): Return<DragonsModel> =
        TODO("Not yet implemented")

}
