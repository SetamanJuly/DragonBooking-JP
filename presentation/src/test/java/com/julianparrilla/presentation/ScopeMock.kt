package com.julianparrilla.presentation

import com.julianparrilla.domain.utils.WithScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlin.coroutines.CoroutineContext

interface ScopeMock {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun withScope() =
        object : WithScope {
            override val coroutineContext: CoroutineContext
                get() = StandardTestDispatcher()
            override val io: CoroutineContext
                get() = StandardTestDispatcher()
            override val jobs: MutableList<Job>
                get() = mutableListOf()
        }
}