package com.julianparrilla.data.datasource.remote

import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.data.utils.Return

interface DragonsRemoteDataSource {

    suspend fun characters(): Return<DragonsModel>

    suspend fun singleCharacter(
        idCharacter: Int
    ): Return<DragonsModel>

}
