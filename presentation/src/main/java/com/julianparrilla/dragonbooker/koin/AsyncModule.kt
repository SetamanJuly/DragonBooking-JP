package com.julianparrilla.dragonbooker.koin

import com.julianparrilla.domain.utils.WithScope
import kotlinx.coroutines.*
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
val asyncModule = module {

    factory<WithScope> {
        object : WithScope {
            override val io: CoroutineContext = Dispatchers.Main.immediate + SupervisorJob()
            override val jobs: MutableList<Job> =  mutableListOf()
            override val coroutineContext: CoroutineContext =  Dispatchers.IO + SupervisorJob()
        }
    }

}