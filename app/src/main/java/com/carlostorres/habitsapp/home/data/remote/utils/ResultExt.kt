package com.carlostorres.habitsapp.home.data.remote.utils

import kotlinx.coroutines.CancellationException

inline fun <T,R> T.resultOf(block: T.() -> R): Result<R>{

    return try {
        Result.success(block())
    }catch (e: CancellationException){
        throw e
    } catch (e: Exception){
        Result.failure(e)
    }

}