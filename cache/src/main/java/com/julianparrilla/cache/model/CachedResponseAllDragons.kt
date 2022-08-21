package com.julianparrilla.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dragonBooking")
data class CachedResponseAllDragons(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "price_original")
    var priceOriginal: Double,

    @ColumnInfo(name = "price")
    var price: Double,

    @ColumnInfo(name = "currency_original")
    var currencyOriginal: String,

    @ColumnInfo(name = "currency")
    var currency: String,

    // INBOUND
    @ColumnInfo(name = "inbound_airline")
    var inboundAirline: String,

    @ColumnInfo(name = "inbound_airline_image")
    var inboundImageAirline: String,

    @ColumnInfo(name = "inbound_arrival_date")
    var inboundArrivalDate: String,

    @ColumnInfo(name = "inbound_arrival_time")
    var inboundArrivalTime: String,

    @ColumnInfo(name = "inbound_departure_date")
    var inboundDepartureDate: String,

    @ColumnInfo(name = "inbound_departure_time")
    var inboundDepartureTime: String,

    @ColumnInfo(name = "inbound_destination")
    var inboundDestination: String,

    @ColumnInfo(name = "inbound_origin")
    var inboundOrigin: String,

    // OUTBOUND
    @ColumnInfo(name = "outbound_airline")
    var outboundAirline: String,

    @ColumnInfo(name = "outbound_airline_image")
    var outboundImageAirline: String,

    @ColumnInfo(name = "outbound_arrival_date")
    var outboundArrivalDate: String,

    @ColumnInfo(name = "outbound_arrival_time")
    var outboundArrivalTime: String,

    @ColumnInfo(name = "outbound_departure_date")
    var outboundDepartureDate: String,

    @ColumnInfo(name = "outbound_departure_time")
    var outboundDepartureTime: String,

    @ColumnInfo(name = "outbound_destination")
    var outboundDestination: String,

    @ColumnInfo(name = "outbound_origin")
    var outboundOrigin: String,

)
