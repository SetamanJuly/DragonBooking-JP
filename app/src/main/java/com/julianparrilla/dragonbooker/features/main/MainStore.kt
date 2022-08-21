package com.julianparrilla.dragonbooker.features.main

import com.julianparrilla.domain.utils.WithScope
import com.julianparrilla.dragonbooker.common.Store

class MainStore(
    withScope: WithScope
) : WithScope by withScope, Store<MainAction, MainState>(MainState()) {

    override fun onInit() {
        MainAction.handle()
    }

    override fun MainAction.reduce(currentState: MainState): MainState {
        return currentState
    }

    override fun MainAction.sideEffects(currentState: MainState) {

    }

}