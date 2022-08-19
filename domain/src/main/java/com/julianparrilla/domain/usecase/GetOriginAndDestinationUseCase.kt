package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take

class GetOriginAndDestinationUseCase(private val repository: DragonsRepository) {

    suspend operator fun invoke(): Return<Pair<List<String>, List<String>>> =
        repository.getOriginAndDestinations().first()

}
