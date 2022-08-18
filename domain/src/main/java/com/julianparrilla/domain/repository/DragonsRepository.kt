package com.julianparrilla.domain.repository

import arrow.core.Either
import com.julianparrilla.domain.model.DragonsDataState
import com.julianparrilla.domain.model.NetworkError
import kotlinx.coroutines.flow.Flow

interface DragonsRepository {

    fun getCharacters(): Flow<Either<NetworkError, DragonsDataState>>

}
