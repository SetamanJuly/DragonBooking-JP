package com.julianparrilla.data.utils

import arrow.core.Either
import com.julianparrilla.domain.model.NetworkError

typealias Return<A> = Either<NetworkError, A>