package com.southafrica.roadguide.data.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.southafrica.roadguide.R
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.model.DriverLicense
import com.southafrica.roadguide.model.Faq
import com.southafrica.roadguide.model.VehicleControl
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AssetsRepositoryImpl @Inject constructor(
    private val gson: Gson,
    @Named("driverFaqs") private val driverFaqsFilename: String,
    @Named("learnerFaqs") private val learnerFaqsFilename: String,
    @Named("driversLicenses") private val driversLicensesFilename: String,
    @Named("vehicleControls") private val vehicleControlsFilename: String,
    @ApplicationContext private val context: Context,
) : AssetsRepository {
    override val driverFaqsFlow: Flow<StateData<List<Faq>>>
        get() = flow {
            emit(StateData.Pending)
            try {
                val jsonString = context.assets.open(driverFaqsFilename).bufferedReader()
                    .use { it.readText() }
                val objectType = object : TypeToken<List<Faq>>() {}.type
                emit(StateData.Success(gson.fromJson(jsonString, objectType)))
            } catch (exception: Exception) {
                emit(StateData.Error(Exception("${R.string.error_loading_driver_faqs}")))
            }
        }

    override val learnerFaqsFlow: Flow<StateData<List<Faq>>>
        get() = flow {
            emit(StateData.Pending)
            try {
                val jsonString = context.assets.open(learnerFaqsFilename).bufferedReader()
                    .use { it.readText() }
                val objectType = object : TypeToken<List<Faq>>() {}.type
                emit(StateData.Success(gson.fromJson(jsonString, objectType)))
            } catch (exception: Exception) {
                emit(StateData.Error(Exception("${R.string.error_loading_learner_faqs}")))
            }
        }

    override val driversLicensesFlow: Flow<StateData<List<DriverLicense>>>
        get() = flow {
            emit(StateData.Pending)
            try {
                val jsonString = context.assets.open(driversLicensesFilename).bufferedReader()
                    .use { it.readText() }
                val objectType = object : TypeToken<List<DriverLicense>>() {}.type
                emit(StateData.Success(gson.fromJson(jsonString, objectType)))
            } catch (exception: Exception) {
                emit(StateData.Error(Exception("${R.string.error_loading_drivers_licenses} ${exception.message}")))
            }
        }

    override val vehicleControlsFlow: Flow<StateData<List<VehicleControl>>>
        get() = flow {
            emit(StateData.Pending)
            try {
                val jsonString = context.assets.open(vehicleControlsFilename).bufferedReader()
                    .use { it.readText() }
                val objectType = object : TypeToken<List<VehicleControl>>() {}.type
                emit(StateData.Success(gson.fromJson(jsonString, objectType)))
            } catch (exception: Exception) {
                emit(StateData.Error(Exception("${R.string.error_loading_vehicle_controls} ${exception.message}")))
            }
        }
}