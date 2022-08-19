package com.julianparrilla.data.datasource.remote

import com.julianparrilla.data.entity.DragonsModel
import com.julianparrilla.domain.utils.Return

interface DragonsRemoteDataSource {

    suspend fun characters(): DragonsModel

    suspend fun singleCharacter(
        idCharacter: Int
    ): Return<DragonsModel>

}
