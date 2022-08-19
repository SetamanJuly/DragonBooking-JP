package com.julianparrilla.remote.model

import com.google.gson.annotations.SerializedName
import com.julianparrilla.data.entity.BoundModel
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.entity.ResultsModel

data class DragonBookerResponse(
    @SerializedName("results") val results: List<Results>
)

data class Results(
    @SerializedName("inbound") val inbound: Bound,
    @SerializedName("outbound") val outbound: Bound,
    @SerializedName("price") val price: Double,
    @SerializedName("currency") val currency: String
)

data class Bound(
    @SerializedName("airline") val airline: String,
    @SerializedName("airlineImage") val airlineImage: String,
    @SerializedName("arrivalDate") val arrivalDate: String,
    @SerializedName("arrivalTime") val arrivalTime: String,
    @SerializedName("departureDate") val departureDate: String,
    @SerializedName("departureTime") val departureTime: String,
    @SerializedName("destination") val destination: String,
    @SerializedName("origin") val origin: String
)

fun DragonBookerResponse.toData(): DragonsModel =
    DragonsModel(
        results = results.map {
            ResultsModel(
                inbound = it.inbound.toData(),
                outbound = it.outbound.toData(),
                price = it.price,
                currency = it.currency,
            )
        }
    )

fun Bound.toData(): BoundModel =
    BoundModel(
        airline = airline,
        airlineImage = airlineImage,
        arrivalDate = arrivalDate,
        arrivalTime = arrivalTime,
        departureDate = departureDate,
        departureTime = departureTime,
        destination = destination,
        origin = origin,
    )
