package com.julianparrilla.remote.service

import com.julianparrilla.remote.model.DragonBookerResponse
import retrofit2.http.GET

interface DragonsApiService {

    @GET("flights")
    suspend fun getDragonList(): DragonBookerResponse
}
