package com.julianparrilla.presentation

import android.os.Bundle
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.dragonbooker.features.main.home.HomeStore
import com.julianparrilla.dragonbooker.features.main.results.ResultInitAction
import com.julianparrilla.dragonbooker.features.main.results.ResultStore
import io.mockk.MockKAnnotations
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultStoreTest : ScopeMock {

    private lateinit var store: ResultStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        store = ResultStore(
            withScope = withScope()
        )
    }

    @Test
    fun `initial check action`() {
        store.onInit()

        assertTrue(store.getActions().isEmpty())
    }

    @Test
    fun `should create action after bundle`() {
        store.onInit()

        val extra: Bundle = Mockito.mock(Bundle::class.java)
        extra.putSerializable(HomeStore.BUNDLE_DATA, DragonsDataState(arrayListOf()))
        extra.putSerializable(HomeStore.BUNDLE_CONVERSION, CurrencyDataState(hashMapOf()))
        store.retrievePrevData(extra)

        assertTrue(store.getActions().last() is ResultInitAction)
    }

}