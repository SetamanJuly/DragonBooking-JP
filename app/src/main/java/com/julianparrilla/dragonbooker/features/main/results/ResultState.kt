package com.julianparrilla.dragonbooker.features.main.results

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.dragonbooker.common.Action
import com.julianparrilla.dragonbooker.common.State

data class ResultState(
    val loading: Boolean = true,
    val data: DragonsDataState? = null,
    val currencies: CurrencyDataState? = null,
) : State

sealed class ResultAction : Action
data class ResultInitAction(val data: DragonsDataState?, val currencies: CurrencyDataState?) :
    ResultAction()