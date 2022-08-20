package com.julianparrilla.dragonbooker.features.main.home

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.model.PriceSort
import com.julianparrilla.dragonbooker.common.Action
import com.julianparrilla.dragonbooker.common.State

data class HomeState(
    val loading: Boolean = true,
    val dragonsDataState: DragonsDataState? = null,
    val originDestination: Pair<List<String>, List<String>>? = null,
    val onOriginChanged: (String) -> Unit = {},
    val onDestinationChanged: (String) -> Unit = {},
    val onMinChanged: (String) -> Unit = {},
    val onMaxChanged: (String) -> Unit = {},
    val onPriceSortChanged: (Boolean) -> Unit = {},
    val onSearchClicked: () -> Unit = {},
    val originSelected: String? = null,
    val priceSort: PriceSort = PriceSort.NONE,
    val destinationSelected: String? = null,
    val minAmountSelected: String? = null,
    val maxAmountSelected: String? = null,
    val currencyState: CurrencyDataState = CurrencyDataState(hashMapOf())
) : State

sealed class HomeAction : Action
object HomeInitAction : HomeAction()
data class HomeSuccessAction(val data: DragonsDataState) : HomeAction()
data class HomeSuccessOriginDestinationAction(val data: Pair<List<String>, List<String>>) : HomeAction()
data class HomeOriginChanged(val origin: String) : HomeAction()
data class HomeDestinationChanged(val destination: String) : HomeAction()
data class HomePriceSortChanged(val priceSort: PriceSort) : HomeAction()
data class HomeMinValueChanged(val min: String) : HomeAction()
data class HomeMaxValueChanged(val max: String) : HomeAction()
data class HomeObtainAllCoins(val list: List<String>) : HomeAction()
data class HomeCoinConversionSuccess(val list: CurrencyDataState) : HomeAction()
object HomeSearchClicked : HomeAction()
object HomeErrorAction : HomeAction()