package com.julianparrilla.presentation

import com.julianparrilla.domain.utils.WithScope
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.UnconfinedTestDispatcher

interface ScopeMock {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun withScope() =
        object : WithScope {
            override val coroutineContext: CoroutineContext
                get() = UnconfinedTestDispatcher()
            override val io: CoroutineContext
                get() = UnconfinedTestDispatcher()
            override val jobs: MutableList<Job>
                get() = mutableListOf()
        }
}
