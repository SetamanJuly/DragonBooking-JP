package com.julianparrilla.data.datasource.cache

import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.utils.Return

interface DragonsCacheDataSource {

    suspend fun insertCharacters(resultsEntity: DragonsModel)

    suspend fun getAllCharacters(): DragonsModel?

    suspend fun getFilteredDragons(query: Pair<String, MutableList<String>>): DragonsModel?

}
