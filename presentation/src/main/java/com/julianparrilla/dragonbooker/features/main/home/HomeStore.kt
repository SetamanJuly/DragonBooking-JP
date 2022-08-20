package com.julianparrilla.dragonbooker.features.main.home

import android.util.Log
import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.PriceSort
import com.julianparrilla.domain.usecase.GetAllDragonsUseCase
import com.julianparrilla.domain.usecase.GetFilteredDragonsUseCase
import com.julianparrilla.domain.usecase.GetOriginAndDestinationUseCase
import com.julianparrilla.domain.utils.WithScope
import com.julianparrilla.dragonbooker.common.Store

class HomeStore(
    withScope: WithScope,
    val getAllDragonsUseCase: GetAllDragonsUseCase,
    val getFilteredDragonsUseCase: GetFilteredDragonsUseCase,
    val getOriginAndDestinationUseCase: GetOriginAndDestinationUseCase,
) : WithScope by withScope, Store<HomeAction, HomeState>(HomeState()) {

    override fun onInit() {
        HomeInitAction.handle()
    }

    override fun HomeAction.reduce(currentState: HomeState): HomeState =
        when (this) {
            HomeInitAction -> currentState.copy(
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
                }
            )
            HomeSearchClicked,
            HomeErrorAction -> currentState
            is HomeSuccessOriginDestinationAction -> currentState.copy(
                loading = false,
                originDestination = data
            )
            is HomeSuccessAction -> currentState.copy(
                loading = false
            )
            is HomeDestinationChanged -> currentState.copy(
                destinationSelected = destination
            )
            is HomeOriginChanged -> currentState.copy(
                originSelected = origin
            )
            is HomeMaxValueChanged -> currentState.copy(
                maxAmountSelected = max
            )
            is HomeMinValueChanged -> currentState.copy(
                minAmountSelected = min
            )
        }

    override fun HomeAction.sideEffects(currentState: HomeState) {
        when (this) {
            is HomeInitAction -> {
                launchIOSafe(
                    f = { getAllDragonsUseCase() },
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
                                priceSort = PriceSort.ASC,
                                originDestination = Pair(
                                    currentState.originSelected ?: "",
                                    currentState.destinationSelected ?: ""
                                ),
                                priceRange = Pair(
                                    currentState.minAmountSelected?.toDouble(),
                                    currentState.maxAmountSelected?.toDouble()
                                )
                            )
                        )
                    },
                    success = {
                        Log.e("SETAMAN", ""+it.results.size)
                    },
                    error = {
                        it
                    }
                )
            }
            else -> {}
        }
    }

}