package com.soethan.movietest.data.utils.ext

import com.soethan.movietest.domain.ErrorResult
import com.soethan.movietest.domain.LoadingResult
import com.soethan.movietest.domain.Result
import com.soethan.movietest.domain.SuccessResult

inline fun <T> result(io: () -> T): Result<T> {
    return try {
        SuccessResult(io.invoke())
    } catch (e: Exception) {
        ErrorResult(e)
    }
}



inline fun <T> resultNullable(io: () -> T?): Result<T?> {
    return try {
        SuccessResult(io.invoke())
    } catch (e: Exception) {
        ErrorResult(e)
    }
}
