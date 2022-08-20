package com.julianparrilla.domain.repository

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.Flow

interface DragonsRepository {

    fun getAllDragons(currency: CurrencyDataState): Flow<Return<DragonsDataState>>

    fun getFilteredData(params: DragonFilterParams, currency: CurrencyDataState): Flow<Return<DragonsDataState>>

    fun getOriginAndDestinations(): Flow<Return<Pair<List<String>, List<String>>>>

    fun getAvailableCoins(): Flow<Return<List<String>>>

}
