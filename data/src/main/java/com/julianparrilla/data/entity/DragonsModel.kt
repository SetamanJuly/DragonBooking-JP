package com.julianparrilla.data.entity

data class DragonsModel(
    val results: List<ResultsModel>
)

data class ResultsModel(
    val inbound: BoundModel,
    val outbound: BoundModel,
    val price: Double,
    val currency: String,
    val priceOriginal: Double?,
    val currencyOriginal: String?
)

data class BoundModel(
    val airline: String,
    val airlineImage: String,
    val arrivalDate: String,
    val arrivalTime: String,
    val departureDate: String,
    val departureTime: String,
    val destination: String,
    val origin: String
)