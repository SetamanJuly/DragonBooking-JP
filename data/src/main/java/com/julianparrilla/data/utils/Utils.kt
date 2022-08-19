package com.julianparrilla.data.utils

import arrow.core.Either
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
            query += " AND"
            query += " inbound_destination=?"
            containsConditions = true
            args.add(it.second)
        }
    }

    if (priceSort != PriceSort.NONE) {
        if (!containsConditions) {
            containsConditions = true
        }

        query += " ORDER BY price"
        when (priceSort) {
            PriceSort.ASC -> query += " ASC"
            else -> query += " DESC"
        }
    }

    if (priceRange != null) {
        if (containsConditions) {
            query += " AND"
        } else {
            query += " WHERE"
            containsConditions = true
        }

        query += " BETWEEN ? AND ?"
        args.add("${priceRange!!.first}")
        args.add("${priceRange!!.second}")
    }

    query += ""

    return Pair(query, args)
}