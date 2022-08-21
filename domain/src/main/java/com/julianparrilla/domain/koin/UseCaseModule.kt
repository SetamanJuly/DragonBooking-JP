package com.julianparrilla.domain.koin

import com.julianparrilla.domain.usecase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val useCaseModule = module {
    factory { GetAllDragonsUseCase(get()) }
    factory { GetFilteredDragonsUseCase(get()) }
    factory { GetOriginAndDestinationUseCase(get()) }
    factory { GetCoinsUseCase(get()) }
    factory { GetObtainConversionsUseCase(get()) }
}
