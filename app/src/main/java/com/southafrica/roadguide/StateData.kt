package com.southafrica.roadguide

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A generic class that holds a value with its loading status.
 * @param <R>
 */
sealed class StateData<out R> {

    object Pending : StateData<Nothing>()
    data class Success<out T>(val data: T) : StateData<T>()
    data class Error(val throwable: Throwable) : StateData<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Pending -> "Pending"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}

/**
 * `true` if [StateData] is of type [StateData.Success] & holds non-null [StateData.Success.isSuccess].
 */
val StateData<*>.isSuccess
    get() = this is StateData.Success && data != null

val StateData<*>.isPending
    get() = this is StateData.Pending

val StateData<*>.isError
    get() = this is StateData.Error

fun <T> StateData<T>.successOr(fallback: T): T {
    return (this as? StateData.Success<T>)?.data ?: fallback
}

fun <T : Any> StateData<T>.successOrThrow(): T {
    when (this) {
        is StateData.Success -> return data
        is StateData.Error -> throw throwable
        else -> throw Exception()
    }
}

val <T> StateData<T>.data: T?
    get() = (this as? StateData.Success)?.data

inline fun <T, R> Flow<StateData<T>>.mapSuccess(crossinline transform: suspend (T) -> R): Flow<StateData<R>> {
    return this.map { stateData ->
        when (stateData) {
            is StateData.Success -> StateData.Success(transform(stateData.data))
            is StateData.Error -> StateData.Error(stateData.throwable)
            else -> StateData.Pending
        }
    }
}