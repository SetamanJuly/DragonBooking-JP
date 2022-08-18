package com.julianparrilla.domain.model

data class DragonsDataState(
    val results: List<ResultsDataState>
)

data class ResultsDataState(
    val inbound: BoundDataState,
    val outbound: BoundDataState,
    val price: Double,
    val currency: String
)

data class BoundDataState(
    val airline: String,
    val airlineImage: String,
    val arrivalDate: String,
    val arrivalTime: String,
    val departureDate: String,
    val departureTime: String,
    val destination: String,
    val origin: String
)