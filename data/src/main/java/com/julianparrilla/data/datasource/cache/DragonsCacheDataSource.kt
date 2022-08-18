package com.julianparrilla.data.datasource.cache

import arrow.core.Either
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.utils.Return
import com.julianparrilla.domain.model.NetworkError

interface DragonsCacheDataSource {

    suspend fun insertCharacters(resultsEntity: DragonsModel)

    suspend fun searchById(idCharacter: Int): DragonsModel?

    suspend fun getAllCharacters(): Return<DragonsModel>?
}
