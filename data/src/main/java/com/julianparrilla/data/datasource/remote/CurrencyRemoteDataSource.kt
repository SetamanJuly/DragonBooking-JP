package com.julianparrilla.data.datasource.remote

import com.julianparrilla.data.entity.CurrencyModel

interface CurrencyRemoteDataSource {

    suspend fun currencyChange(
        from: List<String>,
        to: String
    ): List<CurrencyModel>
}
