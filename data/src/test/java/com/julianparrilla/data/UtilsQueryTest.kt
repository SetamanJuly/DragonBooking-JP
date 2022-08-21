package com.julianparrilla.data

import com.julianparrilla.data.utils.toHashMap
import com.julianparrilla.data.utils.toQuery
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.CurrencyItemState
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.PriceSort
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilsQueryTest {

    @Test
    fun `should get a correct query for the following main params`() {

        val queryExpectedOne =
            "SELECT * FROM dragonBooking WHERE price BETWEEN ? and ? ORDER BY price ASC"
        val queryFilterOne = DragonFilterParams(
            priceSort = PriceSort.ASC,
            priceRange = Pair(0.0, 49.0),
            originDestination = null
        ).toQuery()
        assertEquals(queryExpectedOne, queryFilterOne.first)

        val queryExpectedTwo = "SELECT * FROM dragonBooking WHERE price >=? ORDER BY price ASC"
        val queryFilterTwo = DragonFilterParams(
            priceSort = PriceSort.ASC,
            priceRange = Pair(40.0, null),
            originDestination = null
        ).toQuery()
        assertEquals(queryExpectedTwo, queryFilterTwo.first)

        val queryExpectedThree =
            "SELECT * FROM dragonBooking WHERE inbound_origin=? AND inbound_destination=? AND price <=? ORDER BY price DESC"
        val queryFilterThree = DragonFilterParams(
            priceSort = PriceSort.DESC,
            priceRange = Pair(null, 49.0),
            originDestination = Pair("Test", "Test")
        ).toQuery()
        assertEquals(queryExpectedThree, queryFilterThree.first)

        val queryExpectedFour = "SELECT * FROM dragonBooking ORDER BY price DESC"
        val queryFilterFour = DragonFilterParams().toQuery()
        assertEquals(queryExpectedFour, queryFilterFour.first)

        val queryExpectedFive =
            "SELECT * FROM dragonBooking WHERE inbound_origin=? AND price <=? ORDER BY price DESC"
        val queryFilterFive = DragonFilterParams(
            priceSort = PriceSort.NONE,
            priceRange = Pair(null, 49.0),
            originDestination = Pair("Test", "")
        ).toQuery()
        assertEquals(queryExpectedFive, queryFilterFive.first)
    }

    @Test
    fun `should get correct hashmap conversion`() {
        val currencyTest = CurrencyDataState(
            items = hashMapOf(
                "EUR" to CurrencyItemState("EUR", 1.0),
                "JPY" to CurrencyItemState("EUR", 0.2),
                "GBP" to CurrencyItemState("EUR", 1.2),
                "USD" to CurrencyItemState("EUR", 0.9),
            )
        ).toHashMap()

        assertEquals(0.2, currencyTest["JPY"]!!.exchangeRate, 0.0)
        assertEquals(1.2, currencyTest["GBP"]!!.exchangeRate, 0.0)
        assertEquals(1.0, currencyTest["EUR"]!!.exchangeRate, 0.0)
    }
}