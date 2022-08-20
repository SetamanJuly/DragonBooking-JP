package com.julianparrilla.presentation

import arrow.core.right
import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.usecase.GetAllDragonsUseCase
import com.julianparrilla.domain.usecase.GetFilteredDragonsUseCase
import com.julianparrilla.domain.usecase.GetObtainConversionsUseCase
import com.julianparrilla.domain.usecase.GetOriginAndDestinationUseCase
import com.julianparrilla.dragonbooker.features.main.home.HomeCoinConversionSuccess
import com.julianparrilla.dragonbooker.features.main.home.HomeInitAction
import com.julianparrilla.dragonbooker.features.main.home.HomeStore
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeStoreTest : ScopeMock {

    private lateinit var store: HomeStore

    @MockK
    lateinit var getAllDragonsUseCase: GetAllDragonsUseCase

    @MockK
    lateinit var getFilteredDragonsUseCase: GetFilteredDragonsUseCase

    @MockK
    lateinit var getOriginAndDestinationUseCase: GetOriginAndDestinationUseCase

    @MockK
    lateinit var getObtainConversionsUseCase: GetObtainConversionsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        store = HomeStore(
            withScope = withScope(),
            getAllDragonsUseCase = getAllDragonsUseCase,
            getFilteredDragonsUseCase = getFilteredDragonsUseCase,
            getOriginAndDestinationUseCase = getOriginAndDestinationUseCase,
            getObtainConversionsUseCase = getObtainConversionsUseCase,
        )
    }

    @Test
    fun `initial check action`() {
        store.onInit()

        assertTrue(store.getActions().last() is HomeInitAction)
    }

    @Test
    fun `onInit check next step`() {
        coEvery {
            getObtainConversionsUseCase(
                HomeStore.AVAILABLE_CURRENCIES,
                "EUR"
            )
        } returns CurrencyDataState(
            hashMapOf()
        ).right()

        store.onInit()

        assertTrue(store.getActions().last() is HomeCoinConversionSuccess)
    }


}