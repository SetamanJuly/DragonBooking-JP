package com.julianparrilla.remote.source

import com.julianparrilla.data.datasource.remote.CurrencyRemoteDataSource
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.utils.Return
import com.julianparrilla.remote.model.toData
import com.julianparrilla.remote.service.CurrencyApiService

class CurrencyRemoteDataSourceImpl(
    private val currencyApiService: CurrencyApiService
) : CurrencyRemoteDataSource {

    override suspend fun currencyChange(from: String, to: String): CurrencyModel =
         currencyApiService.getCurrencyConversion(from, to).toData()

}
