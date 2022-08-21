package com.julianparrilla.dragonbooker.koin

import com.julianparrilla.domain.utils.WithScope
import kotlinx.coroutines.*
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

@FlowPreview
@ExperimentalCoroutinesApi
val asyncModule = module {

    val job = SupervisorJob()

    factory<WithScope> {
        object : WithScope {
            override val io: CoroutineContext = Dispatchers.Main.immediate + job
            override val coroutineContext: CoroutineContext = Dispatchers.IO + job
            override val jobs: MutableList<Job> = mutableListOf()
        }
    }

}