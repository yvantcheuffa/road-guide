package com.southafrica.roadguide.model

import java.io.Serializable

data class VehicleControl(
    val iconResName: String,
    val imageResName: String,
    val name: String,
    val descriptions: List<String>
) : Serializable