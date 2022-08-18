package com.julianparrilla.remote.service

import com.julianparrilla.data.utils.Return
import com.julianparrilla.remote.model.CurrencyConverterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("currency")
    suspend fun getCurrencyConversion(
        @Query("from") from: String,
        @Query("to") to: String,
    ): Return<CurrencyConverterResponse>
}
