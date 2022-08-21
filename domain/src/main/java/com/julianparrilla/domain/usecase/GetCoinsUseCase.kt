package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.repository.DragonsRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.last

class GetCoinsUseCase(private val repository: DragonsRepository) {

    // USE THIS FOR FIND ALL THE COINS
    suspend operator fun invoke(): Return<List<String>> =
        repository.getAvailableCoins().last()
}
