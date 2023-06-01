package com.julianparrilla.remote.model

import com.google.gson.annotations.SerializedName
import com.julianparrilla.data.entity.CurrencyModel

data class CurrencyConverterResponse(
    @SerializedName("currencyFrom") val currencyFrom: String,
    @SerializedName("currencyTo") val currencyTo: String,
    @SerializedName("exchangeRate") val exchangeRate: Double
)

fun CurrencyConverterResponse.toData(): CurrencyModel =
    CurrencyModel(
        currencyTo = currencyTo,
        currencyFrom = currencyFrom,
        exchangeRate = exchangeRate
    )
