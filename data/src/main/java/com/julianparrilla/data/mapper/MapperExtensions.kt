package com.julianparrilla.data.mapper

import com.julianparrilla.data.entity.BoundModel
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.domain.model.*

fun DragonsModel.toDomain(): DragonsDataState =
    DragonsDataState(
        results = results.map {
            ResultsDataState(
                inbound = it.inbound.toDomain(),
                outbound = it.outbound.toDomain(),
                priceOriginal = it.priceOriginal,
                currencyOriginal = it.currencyOriginal,
                price = it.price,
                currency = it.currency,
            )
        }
    )

fun BoundModel.toDomain(): BoundDataState =
    BoundDataState(
        airline = airline,
        airlineImage = airlineImage,
        arrivalDate = arrivalDate,
        arrivalTime = arrivalTime,
        departureDate = departureDate,
        departureTime = departureTime,
        destination = destination,
        origin = origin,
    )

fun CurrencyModel.toDomain(): CurrencyItemState =
    CurrencyItemState(
        currency = currency,
        exchangeRate = exchangeRate,
    )

