package com.julianparrilla.data.datasource.cache

import com.julianparrilla.data.entity.DragonsModel

interface DragonsCacheDataSource {

    suspend fun insertCharacters(resultsEntity: DragonsModel)

    suspend fun getAllCharacters(): DragonsModel?

    suspend fun getFilteredDragons(query: Pair<String, MutableList<String>>): DragonsModel?

    suspend fun getOriginAndDestinations(): Pair<List<String>, List<String>>

    suspend fun getAvailableCoins(): List<String>?
}
