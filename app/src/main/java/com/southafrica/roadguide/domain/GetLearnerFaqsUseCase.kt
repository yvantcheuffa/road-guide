package com.southafrica.roadguide.domain

import com.southafrica.roadguide.FlowUseCase
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.data.json.AssetsRepository
import com.southafrica.roadguide.di.IoDispatcher
import com.southafrica.roadguide.model.Faq
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLearnerFaqsUseCase @Inject constructor(
    assetsRepository: AssetsRepository,
    externalScope: CoroutineScope,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Faq>>(ioDispatcher) {

    private val driverFaqFlow = assetsRepository.driverFaqsFlow
        .shareIn(externalScope, SharingStarted.WhileSubscribed(), 1)

    override fun execute(parameter: Unit): Flow<StateData<List<Faq>>> = driverFaqFlow
}
