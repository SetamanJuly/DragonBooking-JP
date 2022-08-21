package com.julianparrilla.remote.source

import com.julianparrilla.data.datasource.remote.DragonsRemoteDataSource
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.remote.model.toData
import com.julianparrilla.remote.service.DragonsApiService

class DragonsRemoteDataSourceImpl(
    private val dragonsApiService: DragonsApiService
) : DragonsRemoteDataSource {

    override suspend fun allDragons(currencyModel: HashMap<String, CurrencyModel>): DragonsModel =
        dragonsApiService.getDragonList().toData(currencyModel)


}
