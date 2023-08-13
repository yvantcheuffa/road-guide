package com.southafrica.roadguide

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [StateData.Error] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<T, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameter: T): Flow<StateData<R>> {
        return execute(parameter)
            .catch { throwable -> emit(StateData.Error(Throwable(throwable))) }
            .flowOn(coroutineDispatcher)
    }

    protected abstract fun execute(parameter: T): Flow<StateData<R>>
}
