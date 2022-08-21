package com.julianparrilla.data.koin

import com.julianparrilla.data.repository.CurrencyRepositoryImpl
import com.julianparrilla.data.repository.DragonsRepositoryImpl
import com.julianparrilla.domain.repository.CurrencyRepository
import com.julianparrilla.domain.repository.DragonsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val dataModule = module {
    factory<DragonsRepository> {
        DragonsRepositoryImpl(
            get(),
            get()
        )
    }

    factory<CurrencyRepository> {
        CurrencyRepositoryImpl(
            get()
        )
    }
}
