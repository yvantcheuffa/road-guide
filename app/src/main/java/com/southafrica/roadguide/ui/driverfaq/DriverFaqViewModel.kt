package com.southafrica.roadguide.ui.driverfaq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southafrica.roadguide.StateData
import com.southafrica.roadguide.domain.GetDriverFaqsUseCase
import com.southafrica.roadguide.model.Faq
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverFaqViewModel @Inject constructor(
    getDriverFaqsUseCase: GetDriverFaqsUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow(DriverFaqUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getDriverFaqsUseCase(Unit).collect(::parseResult)
        }
    }

    private fun parseResult(result: StateData<List<Faq>>) {
        when (result) {
            is StateData.Pending -> _uiState.update {
                it.copy(isLoading = true, errorResId = null)
            }

            is StateData.Success -> _uiState.update {
                it.copy(isLoading = false, faqs = result.data)
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