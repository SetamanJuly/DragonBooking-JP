package com.julianparrilla.dragonbooker.features.main

import com.julianparrilla.dragonbooker.common.Action
import com.julianparrilla.dragonbooker.common.State

data class MainState(
    val loading: Boolean = false
) : State

object MainAction : Action
