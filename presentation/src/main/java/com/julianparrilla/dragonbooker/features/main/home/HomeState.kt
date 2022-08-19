package com.julianparrilla.dragonbooker.features.main.home

import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.dragonbooker.common.Action
import com.julianparrilla.dragonbooker.common.State

data class HomeState(
    val loading: Boolean = false,
    val dragonsDataState: DragonsDataState? = null
): State

sealed class HomeAction: Action
object HomeInitAction: HomeAction()
data class HomeSuccessAction(val data: DragonsDataState): HomeAction()
object HomeErrorAction: HomeAction()