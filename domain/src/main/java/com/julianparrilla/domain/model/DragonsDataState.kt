package com.julianparrilla.domain.model

import java.io.Serializable

data class DragonsDataState(
    val results: List<ResultsDataState>
) : Serializable

data class ResultsDataState(
    val inbound: BoundDataState,
    val outbound: BoundDataState,
    val price: Double,
    val currency: String,
    val priceOriginal: Double?,
    val currencyOriginal: String?
) : Serializable

data class BoundDataState(
    val airline: String,
    val airlineImage: String,
    val arrivalDate: String,
    val arrivalTime: String,
    val departureDate: String,
    val departureTime: String,
    val destination: String,
    val origin: String
) : Serializable