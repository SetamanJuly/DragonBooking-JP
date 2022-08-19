package com.julianparrilla.domain.repository

import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.Flow

interface DragonsRepository {

    fun getCharacters(): Flow<Return<DragonsDataState>>

    fun getFilteredData(params: DragonFilterParams): Flow<Return<DragonsDataState>>

    fun getOriginAndDestinations(): Flow<Return<Pair<List<String>, List<String>>>>

}
