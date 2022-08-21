package com.julianparrilla.dragonbooker.koin

import com.julianparrilla.dragonbooker.features.main.MainActivity
import com.julianparrilla.dragonbooker.features.main.MainStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val mainModule = module {

    factory { MainStore(get()) }
    factory { MainActivity() }

}