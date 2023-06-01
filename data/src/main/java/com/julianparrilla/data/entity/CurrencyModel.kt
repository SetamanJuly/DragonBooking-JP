package com.julianparrilla.data.entity

data class CurrencyModel(
    val currencyTo: String,
    val currencyFrom: String,
    val exchangeRate: Double,
)
