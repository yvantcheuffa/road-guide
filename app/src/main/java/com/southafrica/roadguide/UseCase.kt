package com.southafrica.roadguide

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Base Coroutine Use Case class to executes business logic.
 */
abstract class UseCase<T, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [StateData].
     *
     * @return a [StateData].
     *
     * @param parameter the input parameters to run the use case with
     */
    open suspend operator fun invoke(parameter: T): StateData<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameter).let {
                    StateData.Success(it)
                }
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            StateData.Error(t)
        }
    }

    /**
     * This method should be override to set the code the be executed in the use case.
     */
    protected abstract suspend fun execute(parameter: T): R
}
