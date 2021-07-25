package com.soethan.movietest.domain

sealed class Result<out R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading"
        }
    }

}

fun <T> Result<T>.getDataOrThrow(): T {
    when (this) {
        is Result.Loading -> throw Exception("Data not found")
        is Result.Success ->
            return data
        is Result.Error -> throw throwable
    }
}


val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

typealias ErrorResult = Result.Error

typealias SuccessResult<T> = Result.Success<T>

typealias LoadingResult = Result.Loading

val Result<*>.succeeded
    get() = this is Result.Success && data != null
