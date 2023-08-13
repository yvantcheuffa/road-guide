package com.southafrica.roadguide.model

data class DriverLicense(
    val name: String,
    val description: String,
    val iconResName: String,
    val testReport: TestReport,
    val modules: List<LicenseModule>
)

data class TestReport(
    val name: String,
)

data class LicenseModule(
    val name: String,
    val sections: List<ModuleSection>
)

data class ModuleSection(
    val iconResName: String?,
    val subSections: List<ModuleSubSection>
)

data class ModuleSubSection(
    val name: String,
    val options: List<String>?,
    val detailedDescriptions: List<DetailedDescription>?,
    val descriptions: List<String>?,
    val notes: List<SectionNotes>?
)

data class DetailedDescription(
    val name: String,
    val descriptions: List<String>
)

data class SectionNotes(
    val name: String,
    val description: String,
)