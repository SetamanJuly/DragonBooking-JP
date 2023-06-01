package com.julianparrilla.remote.service

import com.julianparrilla.remote.model.CurrencyConverterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("currency")
    suspend fun getCurrencyConversion(
        @Query("fromCurrency") from: String,
        @Query("toCurrency") to: String,
    ): CurrencyConverterResponse
}
