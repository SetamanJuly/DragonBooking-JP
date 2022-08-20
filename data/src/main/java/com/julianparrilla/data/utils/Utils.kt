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

        if(it.first != null && it.second != null) {
            query += " price BETWEEN ? AND ?"
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

    query += ""

    return Pair(query, args)
}