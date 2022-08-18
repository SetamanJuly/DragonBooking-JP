package com.julianparrilla.data.datasource.remote

import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.utils.Return

interface CurrencyRemoteDataSource {

    suspend fun currencyChange(
        from: String,
        to: String
    ): Return<CurrencyModel>

}
