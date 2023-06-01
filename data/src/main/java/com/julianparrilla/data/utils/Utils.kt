package com.julianparrilla.data.utils

import arrow.core.Either
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.NetworkError
import com.julianparrilla.domain.model.PriceSort

typealias Return<A> = Either<NetworkError, A>

fun DragonFilterParams.toQuery(): Pair<String, MutableList<String>> {

    var query = "SELECT * FROM dragonBooking"
    var containsConditions = false
    val args: MutableList<String> = mutableListOf()

    originDestination?.let {
        if (it.first.isNotEmpty()) {
            query += " WHERE"
            query += " inbound_origin=?"
            containsConditions = true
            args.add(it.first)
        }

        if (it.second.isNotEmpty()) {
            if (containsConditions) {
                query += " AND"
            } else {
                query += " WHERE"
                containsConditions = true
            }
            query += " inbound_destination=?"
            args.add(it.second)
        }
    }

    priceRange?.let {
        if (containsConditions) {
            query += " AND"
        } else {
            query += " WHERE"
            containsConditions = true
        }

        if (it.first != null && it.second != null) {
            query += " price BETWEEN ? and ?"
            args.add("${it.first}")
            args.add("${it.second}")
        } else if (it.second != null) {
            query += " price <=?"
            args.add("${it.second}")
        } else {
            query += " price >=?"
            args.add("${it.first ?: 0.0}")
        }
    }

    query += " ORDER BY price"
    query += when (priceSort) {
        PriceSort.ASC -> " ASC"
        else -> " DESC"
    }

    return Pair(query, args)
}

fun CurrencyDataState.toHashMap(): HashMap<String, CurrencyModel> {
    val hashMap = hashMapOf<String, CurrencyModel>()
    items.map {
        hashMap.put(
            it.key,
            CurrencyModel(
                currencyTo = it.value.currency,
                currencyFrom = it.value.currency,
                exchangeRate = it.value.exchangeRate
            )
        )
    }
    return hashMap
}
