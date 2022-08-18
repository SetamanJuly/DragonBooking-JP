package com.julianparrilla.remote.koin

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
const val TIME_OUT = 30L

val remoteModule = module {

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    //factory { provideMarvelAPI(get()) }

    //factory<MarvelRemoteDataSource> {
    //    MarvelRemoteDataSourceImpl(
    //        get()
    //    )
    //}
}

//fun provideMarvelAPI(retrofit: Retrofit): MarvelApiService =
//    retrofit.create(MarvelApiService::class.java)
