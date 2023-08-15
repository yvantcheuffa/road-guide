package com.southafrica.roadguide.ui.driver.license

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.domain.GetDriversLicensesUseCase
import com.southafrica.roadguide.model.DriverLicense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverLicenceViewModel @Inject constructor(
    getDriversLicensesUseCase: GetDriversLicensesUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow(DriverLicenseUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getDriversLicensesUseCase(Unit).collect(::parseResult)
        }
    }

    private fun parseResult(result: StateData<List<DriverLicense>>) {
        when (result) {
            is StateData.Pending -> _uiState.update {
                it.copy(isLoading = true, errorResId = null)
            }

            is StateData.Success -> _uiState.update {
                it.copy(isLoading = false, driversLicenses = result.data)
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

data class DriverLicenseUiState(
    val driversLicenses: List<DriverLicense>? = null,
    val isLoading: Boolean = true,
    @StringRes val errorResId: Int? = null,
)