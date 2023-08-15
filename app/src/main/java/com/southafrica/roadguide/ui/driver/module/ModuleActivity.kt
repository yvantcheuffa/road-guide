package com.southafrica.roadguide.ui.driver.module

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.databinding.ActivityModuleBinding
import com.southafrica.roadguide.databinding.ItemLicenseModuleBinding
import com.southafrica.roadguide.databinding.ItemModuleSectionBinding
import com.southafrica.roadguide.model.DriverLicense
import com.southafrica.roadguide.model.LicenseModule
import com.southafrica.roadguide.model.ModuleSection
import com.southafrica.roadguide.ui.driver.section.ModuleSectionActivity
import com.southafrica.roadguide.ui.driver.section.ModuleSectionActivity.Companion.ARG_MODULE_SECTION
import com.southafrica.roadguide.ui.driver.section.ModuleSectionActivity.Companion.ARG_MODULE_SECTION_TITLE

class ModuleActivity : AppCompatActivity() {
    private lateinit var driverLicense: DriverLicense

    private lateinit var binding: ActivityModuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        driverLicense = intent.getSerializableExtra(ARG_DRIVER_LICENSE) as DriverLicense
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = driverLicense.title

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            val iconResId =
                resources.getIdentifier(driverLicense.iconResName, "drawable", packageName)
            val content = SpannableString("${driverLicense.name} Modules")
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            icon.setImageResource(iconResId)
            name.text = driverLicense.name
            title.text = content
            description.text = driverLicense.description

            displayModules(driverLicense.modules)
        }
    }

    private fun displayModules(modules: List<LicenseModule>) {
        binding.modulesContainer.removeAllViews()
        for (licenseModule in modules) {
            val licenseModuleBinding =
                ItemLicenseModuleBinding.inflate(layoutInflater, binding.modulesContainer, false)
            val content = SpannableString(licenseModule.name)
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            licenseModuleBinding.name.text = content

            for ((licenseModuleIndex, moduleSection) in licenseModule.sections.withIndex()) {
                val moduleSectionBinding = ItemModuleSectionBinding.inflate(
                    layoutInflater,
                    licenseModuleBinding.moduleSectionsContainer,
                    false
                )
                moduleSectionBinding.txvSection.text =
                    "${licenseModuleIndex.plus(1)}. ${moduleSection.name}"
                moduleSectionBinding.root.setOnClickListener {
                    startModuleSectionActivity(moduleSection)
                }
                licenseModuleBinding.moduleSectionsContainer.addView(moduleSectionBinding.root)
            }
            binding.modulesContainer.addView(licenseModuleBinding.root)
        }
    }

    private fun startModuleSectionActivity(moduleSection: ModuleSection) {
        val intent = Intent(this, ModuleSectionActivity::class.java)
        intent.putExtra(ARG_MODULE_SECTION, moduleSection)
        intent.putExtra(ARG_MODULE_SECTION_TITLE, driverLicense.name)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_DRIVER_LICENSE = "ARG_DRIVER_LICENSE"
    }
}