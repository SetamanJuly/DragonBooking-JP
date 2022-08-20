package com.julianparrilla.domain.model

import java.io.Serializable

data class CurrencyDataState(
    val items: HashMap<String, CurrencyItemState>
) : Serializable

data class CurrencyItemState(
    val currency: String,
    val exchangeRate: Double,
) : Serializable

fun ResultsDataState.toCurrency(currency: CurrencyDataState): String =
    currency.items[this.currency]?.let {
        val price = this@toCurrency.price * it.exchangeRate
        if (it.currency != this@toCurrency.currencyOriginal)
            "(Original: ${(this@toCurrency.priceOriginal ?: 0.0).round()}${this@toCurrency.currencyOriginal}) -> ${price.round()} ${it.currency}"
        else
            "${price.round()} ${it.currency}"
    } ?: ""

fun Double.round(decimals: Int = 2): Double =
    "%.${decimals}f".format(this).replace(",", ".").toDouble()