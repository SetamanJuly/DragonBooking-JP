package com.julianparrilla.domain.koin

import com.julianparrilla.domain.usecase.GetAllDragonsUseCase
import com.julianparrilla.domain.usecase.GetFilteredDragonsUseCase
import com.julianparrilla.domain.utils.WithScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val useCaseModule = module {
    factory { GetAllDragonsUseCase(get()) }
    factory { GetFilteredDragonsUseCase(get()) }
}
