package com.southafrica.roadguide.ui

import androidx.annotation.StringRes
import com.southafrica.roadguide.model.Faq

data class FaqUiState(
    val faqs: List<Faq>? = null,
    val isLoading: Boolean = true,
    @StringRes
    val errorResId: Int? = null,
)