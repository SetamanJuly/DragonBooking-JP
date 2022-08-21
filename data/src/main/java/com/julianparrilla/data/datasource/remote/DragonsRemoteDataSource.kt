package com.julianparrilla.data.datasource.remote

import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.entity.DragonsModel

interface DragonsRemoteDataSource {

    suspend fun allDragons(currencyModel: HashMap<String, CurrencyModel>): DragonsModel

}
