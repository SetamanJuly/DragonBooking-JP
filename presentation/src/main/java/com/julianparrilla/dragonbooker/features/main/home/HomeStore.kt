package com.julianparrilla.dragonbooker.features.main.home

import android.os.Bundle
import androidx.navigation.NavController
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.PriceSort
import com.julianparrilla.domain.usecase.*
import com.julianparrilla.domain.utils.WithScope
import com.julianparrilla.dragonbooker.R
import com.julianparrilla.dragonbooker.common.Store

class HomeStore(
    withScope: WithScope,
    val getAllDragonsUseCase: GetAllDragonsUseCase,
    val getFilteredDragonsUseCase: GetFilteredDragonsUseCase,
    val getOriginAndDestinationUseCase: GetOriginAndDestinationUseCase,
    val getObtainConversionsUseCase: GetObtainConversionsUseCase
) : WithScope by withScope, Store<HomeAction, HomeState>(HomeState()) {

    lateinit var navigator: NavController

    override fun onInit() {
        HomeInitAction.handle()
    }

    override fun HomeAction.reduce(currentState: HomeState): HomeState =
        when (this) {
            HomeInitAction -> currentState.copy(
                loading = true,
                onDestinationChanged = {
                    HomeDestinationChanged(it).handle()
                },
                onOriginChanged = {
                    HomeOriginChanged(it).handle()
                },
                onMaxChanged = {
                    HomeMaxValueChanged(it).handle()
                },
                onMinChanged = {
                    HomeMinValueChanged(it).handle()
                },
                onSearchClicked = {
                    HomeSearchClicked.handle()
                },
                onPriceSortChanged = {
                    HomePriceSortChanged(if (it) PriceSort.ASC else PriceSort.DESC).handle()
                }
            )
            is HomeSuccessOriginDestinationAction -> currentState.copy(
                originDestination = data
            )
            is HomeDestinationChanged -> currentState.copy(
                destinationSelected = destination.ifEmpty { null }
            )
            is HomeOriginChanged -> currentState.copy(
                originSelected = origin.ifEmpty { null }
            )
            is HomeMaxValueChanged -> currentState.copy(
                maxAmountSelected = max.ifEmpty { null }
            )
            is HomeMinValueChanged -> currentState.copy(
                minAmountSelected = min.ifEmpty { null }
            )
            is HomePriceSortChanged -> currentState.copy(
                priceSort = priceSort
            )
            is HomeCoinConversionSuccess -> currentState.copy(
                currencyState = list,
                loading = false
            )
            is HomeSuccessAction,
            is HomeObtainAllCoins,
            HomeSearchClicked,
            HomeErrorAction -> currentState
        }

    override fun HomeAction.sideEffects(currentState: HomeState) {
        when (this) {
            is HomeInitAction -> {
                launchIOSafe(
                    f = { getObtainConversionsUseCase(arrayListOf("EUR", "USD", "GBP", "JPY"), "EUR") },
                    success = {
                        HomeCoinConversionSuccess(it).handle()
                    },
                    error = {
                        it
                    }
                )
            }
            is HomeCoinConversionSuccess -> {
                launchIOSafe(
                    f = { getAllDragonsUseCase(list) },
                    success = {
                        HomeSuccessAction(it).handle()
                    },
                    error = {
                        it
                    }
                )
            }
            is HomeSuccessAction -> {
                launchIOSafe(
                    f = { getOriginAndDestinationUseCase() },
                    success = {
                        HomeSuccessOriginDestinationAction(it).handle()
                    },
                    error = {
                        it
                    }
                )
            }
            HomeSearchClicked -> {
                launchIOSafe(
                    f = {
                        getFilteredDragonsUseCase(
                            DragonFilterParams(
                                priceSort = currentState.priceSort,
                                originDestination = Pair(
                                    currentState.originSelected ?: "",
                                    currentState.destinationSelected ?: ""
                                ),
                                priceRange = Pair(
                                    currentState.minAmountSelected?.toDouble(),
                                    currentState.maxAmountSelected?.toDouble()
                                )
                            ),
                            currentState.currencyState
                        )
                    },
                    success = {
                        launchIO {
                            val bundle = Bundle()
                            bundle.putSerializable(BUNDLE_DATA, it)
                            bundle.putSerializable(BUNDLE_CONVERSION, currentState.currencyState)
                            navigator.navigate(R.id.action_homeFragment_to_registerFragment, bundle)
                        }
                    },
                    error = {
                        it
                    }
                )
            }
            else -> {}
        }
    }

    companion object {
        const val BUNDLE_DATA = "DATA"
        const val BUNDLE_CONVERSION = "CONVERSION"
    }
}