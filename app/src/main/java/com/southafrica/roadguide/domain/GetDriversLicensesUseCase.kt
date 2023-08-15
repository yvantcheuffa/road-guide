package com.southafrica.roadguide.domain

import com.southafrica.roadguide.FlowUseCase
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.data.json.AssetsRepository
import com.southafrica.roadguide.di.IoDispatcher
import com.southafrica.roadguide.model.DriverLicense
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDriversLicensesUseCase @Inject constructor(
    assetsRepository: AssetsRepository,
    externalScope: CoroutineScope,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<DriverLicense>>(ioDispatcher) {

    private val driversLicensesFlow = assetsRepository.driversLicensesFlow
        .shareIn(externalScope, SharingStarted.WhileSubscribed(), 1)

    override fun execute(parameter: Unit): Flow<StateData<List<DriverLicense>>> = driversLicensesFlow
}
