package com.julianparrilla.remote.model

import com.google.gson.annotations.SerializedName
import com.julianparrilla.data.entity.CurrencyModel

data class CurrencyConverterResponse(
    @SerializedName("currency") val currency: String,
    @SerializedName("exchangeRate") val exchangeRate: Double
)

fun CurrencyConverterResponse.toData(): CurrencyModel =
    CurrencyModel(
        currency = currency,
        exchangeRate = exchangeRate
    )
