package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.last

class GetAllDragonsUseCase(private val repository: DragonsRepository) {

    suspend operator fun invoke(currency: CurrencyDataState): Return<DragonsDataState> =
        repository.getAllDragons(currency).last()

}