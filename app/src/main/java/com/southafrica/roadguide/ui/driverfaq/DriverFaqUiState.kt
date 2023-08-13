package com.southafrica.roadguide.ui.driverfaq

import androidx.annotation.StringRes
import com.southafrica.roadguide.model.Faq

data class DriverFaqUiState(
    val faqs: List<Faq>? = null,
    val isLoading: Boolean = true,
    @StringRes val errorResId: Int? = null,
)