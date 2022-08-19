package com.julianparrilla.dragonbooker.features.main.home

import com.julianparrilla.domain.model.DragonFilterParams
import com.julianparrilla.domain.model.PriceSort
import com.julianparrilla.domain.usecase.GetAllDragonsUseCase
import com.julianparrilla.domain.usecase.GetFilteredDragonsUseCase
import com.julianparrilla.domain.utils.WithScope
import com.julianparrilla.dragonbooker.common.Store

class HomeStore(
    withScope: WithScope,
    val getAllDragonsUseCase: GetAllDragonsUseCase,
    val getFilteredDragonsUseCase: GetFilteredDragonsUseCase,
) : WithScope by withScope, Store<HomeAction, HomeState>(HomeState()) {

    override fun onInit() {
        HomeInitAction.handle()
    }

    override fun HomeAction.reduce(currentState: HomeState): HomeState =
        when (this) {
            HomeInitAction,
            HomeErrorAction -> currentState
            is HomeSuccessAction -> currentState
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
                    f = { getFilteredDragonsUseCase(DragonFilterParams(priceSort = PriceSort.ASC)) },
                    success = {
                        it
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