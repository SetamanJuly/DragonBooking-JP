package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetAllDragonsUseCase(private val repository: DragonsRepository) {

    suspend operator fun invoke(): Return<DragonsDataState> =
        repository.getCharacters().last()

}