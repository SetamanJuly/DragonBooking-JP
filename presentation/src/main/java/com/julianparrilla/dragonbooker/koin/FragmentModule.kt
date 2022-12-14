package com.julianparrilla.dragonbooker.koin

import com.julianparrilla.dragonbooker.features.main.home.HomeFragment
import com.julianparrilla.dragonbooker.features.main.home.HomeStore
import com.julianparrilla.dragonbooker.features.main.results.ResultFragment
import com.julianparrilla.dragonbooker.features.main.results.ResultStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val fragmentModule = module {

    factory { HomeStore(get(), get(), get(), get(), get()) }
    factory { HomeFragment() }

    factory { ResultStore(get()) }
    factory { ResultFragment() }
}
