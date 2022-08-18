package com.julianparrilla.dragonbooker.koin

import com.julianparrilla.cache.koin.databaseModule
import com.julianparrilla.data.koin.dataModule
import com.julianparrilla.domain.koin.useCaseModule
import com.julianparrilla.remote.koin.remoteModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.core.module.Module

@FlowPreview
@ExperimentalCoroutinesApi
val koinModules = listOf(
    databaseModule,
    remoteModule,
    dataModule,
    useCaseModule,
    fragmentModule,
    mainModule
)
