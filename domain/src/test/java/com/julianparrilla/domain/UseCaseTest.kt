package com.julianparrilla.domain

import com.julianparrilla.domain.model.CurrencyDataState
import com.julianparrilla.domain.usecase.GetAllDragonsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    @MockK
    private lateinit var getAllDragonsUseCase: GetAllDragonsUseCase

    @Test
    fun `useCase should give me something`() {
        coEvery { getAllDragonsUseCase(CurrencyDataState(hashMapOf())) }
        verify {  }
    }

}