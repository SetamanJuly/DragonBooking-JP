package com.julianparrilla.cache.source

import androidx.sqlite.db.SimpleSQLiteQuery
import com.julianparrilla.cache.db.DragonsDao
import com.julianparrilla.cache.model.CachedResponseAllDragons
import com.julianparrilla.data.datasource.cache.DragonsCacheDataSource
import com.julianparrilla.data.entity.BoundModel
import com.julianparrilla.data.entity.CurrencyModel
import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.entity.ResultsModel

class DragonsCacheDataSourceImpl(
    private val dragonsDao: DragonsDao
) : DragonsCacheDataSource {

    override suspend fun insertCharacters(resultsEntity: DragonsModel) {
        dragonsDao.deleteAll()
        dragonsDao.addAll(
            resultsEntity.results.map {
                CachedResponseAllDragons(
                    priceOriginal = it.priceOriginal ?: 0.0,
                    currencyOriginal = it.currencyOriginal ?: "",
                    price = it.price,
                    currency = it.currency,
                    inboundAirline = it.inbound.airline,
                    inboundImageAirline = it.inbound.airlineImage,
                    inboundArrivalDate = it.inbound.arrivalDate,
                    inboundArrivalTime = it.inbound.arrivalTime,
                    inboundDepartureDate = it.inbound.departureDate,
                    inboundDepartureTime = it.inbound.departureTime,
                    inboundDestination = it.inbound.destination,
                    inboundOrigin = it.inbound.origin,
                    outboundAirline = it.outbound.airline,
                    outboundImageAirline = it.outbound.airlineImage,
                    outboundArrivalDate = it.outbound.arrivalDate,
                    outboundArrivalTime = it.outbound.arrivalTime,
                    outboundDepartureDate = it.outbound.departureDate,
                    outboundDepartureTime = it.outbound.departureTime,
                    outboundDestination = it.outbound.destination,
                    outboundOrigin = it.outbound.origin,
                )
            }
        )
    }

    override suspend fun getAllCharacters(): DragonsModel? = dragonsDao.getCachedData()?.toData()

    override suspend fun getFilteredDragons(query: Pair<String, MutableList<String>>): DragonsModel? =
        dragonsDao.getFilteredData(SimpleSQLiteQuery(query.first, query.second.toTypedArray()))
            ?.toData()

    override suspend fun getOriginAndDestinations(): Pair<List<String>, List<String>> = Pair(
        dragonsDao.getOrigins(),
        dragonsDao.getDestinations()
    )

    override suspend fun getAvailableCoins(): List<String> =
        dragonsDao.getCoins()

    private fun List<CachedResponseAllDragons>.toData(): DragonsModel =
        DragonsModel(
            results = map {
                ResultsModel(
                    price = it.price,
                    currency = it.currency,
                    priceOriginal = it.priceOriginal,
                    currencyOriginal = it.currencyOriginal,
                    inbound = BoundModel(
                        airline = it.inboundAirline,
                        airlineImage = it.inboundImageAirline,
                        arrivalDate = it.inboundArrivalDate,
                        arrivalTime = it.inboundArrivalTime,
                        departureDate = it.inboundDepartureDate,
                        departureTime = it.inboundDepartureTime,
                        destination = it.inboundDestination,
                        origin = it.inboundOrigin
                    ),
                    outbound = BoundModel(
                        airline = it.outboundAirline,
                        airlineImage = it.outboundImageAirline,
                        arrivalDate = it.outboundArrivalDate,
                        arrivalTime = it.outboundArrivalTime,
                        departureDate = it.outboundDepartureDate,
                        departureTime = it.outboundDepartureTime,
                        destination = it.outboundDestination,
                        origin = it.outboundOrigin
                    ),
                )
            }
        )
}
