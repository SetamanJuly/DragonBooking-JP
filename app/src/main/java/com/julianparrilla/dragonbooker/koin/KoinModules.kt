package com.julianparrilla.dragonbooker.koin

import com.julianparrilla.cache.koin.databaseModule
import com.julianparrilla.data.koin.dataModule
import com.julianparrilla.domain.koin.useCaseModule
import com.julianparrilla.remote.koin.remoteCurrencyModule
import com.julianparrilla.remote.koin.remoteDragonsModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
val koinModules = listOf(
    databaseModule,
    remoteDragonsModule,
    remoteCurrencyModule,
    dataModule,
    useCaseModule,
    fragmentModule,
    mainModule
)
