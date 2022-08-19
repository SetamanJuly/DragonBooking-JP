package com.julianparrilla.data.utils

import arrow.core.Either
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.NetworkError
import com.julianparrilla.domain.model.PriceSort

typealias Return<A> = Either<NetworkError, A>

fun DragonFilterParams.toQuery() : Pair<String, MutableList<String>> {

    var query = "SELECT * FROM dragonBooking"
    var containsConditions = false
    val args: MutableList<String> = mutableListOf()

    if(!name.isNullOrEmpty()){
        query += " WHERE"
        query += " outboundDestination LIKE ?%"
        containsConditions = true
        args.add(name!!)
    }

    if(priceSort != PriceSort.NONE){
        if (!containsConditions) {
            containsConditions = true
        }

        query += " ORDER BY price"
        when(priceSort){
            PriceSort.ASC -> query += " ASC"
            else -> query += " DESC"
        }
    }

    if(priceRange != null){
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