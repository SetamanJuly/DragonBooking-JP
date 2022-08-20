package com.julianparrilla.data.datasource.remote

import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.domain.utils.Return

interface CurrencyRemoteDataSource {

    suspend fun currencyChange(
        from: List<String>,
        to: String
    ): List<CurrencyModel>

}
