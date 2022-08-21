package com.julianparrilla.domain.model

data class DragonFilterParams(
    val priceSort: PriceSort = PriceSort.NONE,
    val priceRange: Pair<Double?, Double?>? = null,
    val originDestination: Pair<String, String>? = null,
)

enum class PriceSort {
    ASC, DESC, NONE
}