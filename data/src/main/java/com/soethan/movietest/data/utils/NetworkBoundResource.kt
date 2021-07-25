package com.soethan.movietest.data.utils

import com.soethan.movietest.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },

) = flow {
    val data = query().first()

    val flow = if(shouldFetch(data)){
            try {
                saveFetchResult(fetch())
                query().map {
                    Result.Success(it)
                }
            }catch (throwable:Throwable){
                query().map { Result.Error(throwable) }
            }
        }else{
            query().map { Result.Success(it) }
        }

    emitAll(flow)
}
