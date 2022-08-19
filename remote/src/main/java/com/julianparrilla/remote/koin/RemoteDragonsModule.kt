package com.julianparrilla.remote.koin

import com.julianparrilla.data.datasource.remote.DragonsRemoteDataSource
import com.julianparrilla.remote.service.DragonsApiService
import com.julianparrilla.remote.source.DragonsRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDragonsModule = module {

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(BASE_URL_DRAGONS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    factory { provideDragonsApi(get()) }

    factory<DragonsRemoteDataSource> {
        DragonsRemoteDataSourceImpl(
            get()
        )
    }
}

fun provideDragonsApi(retrofit: Retrofit): DragonsApiService =
    retrofit.create(DragonsApiService::class.java)
