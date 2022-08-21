package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.last

class GetFilteredDragonsUseCase(private val repository: DragonsRepository) {

    suspend operator fun invoke(
        params: DragonFilterParams,
        currency: CurrencyDataState
    ): Return<DragonsDataState> =
        repository.getFilteredData(params, currency).last()
}
