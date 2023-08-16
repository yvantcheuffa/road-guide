package com.southafrica.roadguide.domain

import com.southafrica.roadguide.FlowUseCase
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.data.json.AssetsRepository
import com.southafrica.roadguide.di.IoDispatcher
import com.southafrica.roadguide.model.VehicleControl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVehicleControlsUseCase @Inject constructor(
    assetsRepository: AssetsRepository,
    externalScope: CoroutineScope,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<VehicleControl>>(ioDispatcher) {

    private val vehicleControlsFlow = assetsRepository.vehicleControlsFlow
        .shareIn(externalScope, SharingStarted.WhileSubscribed(), 1)

    override fun execute(parameter: Unit): Flow<StateData<List<VehicleControl>>> =
        vehicleControlsFlow
}
