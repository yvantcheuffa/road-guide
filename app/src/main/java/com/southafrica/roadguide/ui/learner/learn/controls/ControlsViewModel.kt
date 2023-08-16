package com.southafrica.roadguide.ui.learner.learn.controls

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.domain.GetVehicleControlsUseCase
import com.southafrica.roadguide.model.VehicleControl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlsViewModel @Inject constructor(
    getVehicleControlsUseCase: GetVehicleControlsUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(ControlsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getVehicleControlsUseCase(Unit).collect(::parseResult)
        }
    }

    private fun parseResult(result: StateData<List<VehicleControl>>) {
        when (result) {
            is StateData.Pending -> _uiState.update {
                it.copy(isLoading = true, errorResId = null)
            }

            is StateData.Success -> _uiState.update {
                it.copy(isLoading = false, vehicleControls = result.data)
            }

            is StateData.Error -> _uiState.update { uiState ->
                uiState.copy(
                    errorResId = result.throwable.message!!.toInt(),
                    isLoading = false
                )
            }
        }
    }
}

data class ControlsUiState(
    val vehicleControls: List<VehicleControl>? = null,
    val isLoading: Boolean = true,
    @StringRes
    val errorResId: Int? = null,
)