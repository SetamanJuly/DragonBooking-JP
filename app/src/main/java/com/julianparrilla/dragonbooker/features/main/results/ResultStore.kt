package com.julianparrilla.dragonbooker.features.main.results

import android.os.Bundle
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.utils.WithScope
import com.julianparrilla.dragonbooker.common.Store
import com.julianparrilla.dragonbooker.features.main.home.HomeStore.Companion.BUNDLE_CONVERSION
import com.julianparrilla.dragonbooker.features.main.home.HomeStore.Companion.BUNDLE_DATA
import org.jetbrains.kotlin.utils.doNothing

class ResultStore(
    withScope: WithScope
) : WithScope by withScope, Store<ResultAction, ResultState>(ResultState()) {

    override fun onInit() {
        doNothing()
    }

    fun retrievePrevData(arguments: Bundle?) {
        arguments?.let {
            ResultInitAction(
                it.getSerializable(BUNDLE_DATA) as? DragonsDataState,
                it.getSerializable(BUNDLE_CONVERSION) as? CurrencyDataState,
            ).handle()
        }
    }

    override fun ResultAction.reduce(currentState: ResultState): ResultState =
        when (this) {
            is ResultInitAction -> currentState.copy(
                data = data,
                currencies = currencies
            )
        }

    override fun ResultAction.sideEffects(currentState: ResultState) {}

}