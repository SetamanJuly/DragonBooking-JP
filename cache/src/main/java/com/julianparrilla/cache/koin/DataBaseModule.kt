package com.julianparrilla.cache.koin

import androidx.room.Room
import com.julianparrilla.cache.db.AppDatabase
import com.julianparrilla.cache.source.DragonsCacheDataSourceImpl
import com.julianparrilla.data.datasource.cache.DragonsCacheDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().getDragonsDao() }

    factory<DragonsCacheDataSource> {
        DragonsCacheDataSourceImpl(
            get()
        )
    }
}
