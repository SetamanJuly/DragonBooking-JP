package com.julianparrilla.domain.usecase

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.repository.CurrencyRepository
import com.julianparrilla.domain.utils.Return
import kotlinx.coroutines.flow.last

class GetObtainConversionsUseCase(private val repository: CurrencyRepository) {

    suspend operator fun invoke(list: List<String>, to: String): Return<CurrencyDataState> =
        repository.getCurrencyConversion(list, to).last()

}
