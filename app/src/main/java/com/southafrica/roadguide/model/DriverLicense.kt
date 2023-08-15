package com.southafrica.roadguide.model

import java.io.Serializable

data class DriverLicense(
    val title: String,
    val name: String,
    val description: String,
    val iconResName: String,
    val testReport: TestReport,
    val modules: List<LicenseModule>
) : Serializable

data class TestReport(
    val name: String,
) : Serializable

data class LicenseModule(
    val name: String,
    val sections: List<ModuleSection>
) : Serializable

data class ModuleSection(
    val name: String,
    val iconResName: String?,
    val subSections: List<ModuleSubSection>
) : Serializable

data class ModuleSubSection(
    val name: String,
    val options: List<String>?,
    val detailedDescriptions: List<DetailedDescription>?,
    val descriptions: List<String>?,
    val notes: List<SectionNote>?
) : Serializable

data class DetailedDescription(
    val name: String,
    val descriptions: List<String>
) : Serializable

data class SectionNote(
    val name: String,
    val description: String,
) : Serializable