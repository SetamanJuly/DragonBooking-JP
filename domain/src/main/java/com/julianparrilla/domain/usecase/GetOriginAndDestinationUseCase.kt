package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.last

class GetOriginAndDestinationUseCase(private val repository: DragonsRepository) {

    suspend operator fun invoke(): Return<Pair<List<String>, List<String>>> =
        repository.getOriginAndDestinations().last()

}
