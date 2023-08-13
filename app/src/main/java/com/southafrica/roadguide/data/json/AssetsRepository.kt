package com.southafrica.roadguide.data.json

import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.model.Faq
import kotlinx.coroutines.flow.Flow

interface AssetsRepository {
    val driverFaqsFlow: Flow<StateData<List<Faq>>>

    val learnerFaqsFlow: Flow<StateData<List<Faq>>>
}