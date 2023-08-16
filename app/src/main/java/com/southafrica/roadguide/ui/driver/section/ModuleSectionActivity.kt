package com.southafrica.roadguide.ui.driver.section

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.R
import com.southafrica.roadguide.Utils.getUnderlinedText
import com.southafrica.roadguide.databinding.ActivityModuleSectionBinding
import com.southafrica.roadguide.databinding.ItemDetailedDescriptionBinding
import com.southafrica.roadguide.databinding.ItemNoteBinding
import com.southafrica.roadguide.databinding.ItemSubModuleBinding
import com.southafrica.roadguide.databinding.ItemTextviewBinding
import com.southafrica.roadguide.model.ModuleSection

class ModuleSectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModuleSectionBinding
    private lateinit var moduleSection: ModuleSection
    private lateinit var moduleSectionTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moduleSection = intent.getSerializableExtra(ARG_MODULE_SECTION) as ModuleSection
        moduleSectionTitle = intent.getStringExtra(ARG_MODULE_SECTION_TITLE)!!

        binding = ActivityModuleSectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.module_section)

        setupViews()
    }

    private fun setupViews() {
        moduleSection.iconResName?.let {
            val iconResId =
                resources.getIdentifier(moduleSection.iconResName, "drawable", packageName)
            binding.icon.setImageResource(iconResId)
        } ?: run { binding.icon.visibility = GONE }
        binding.title.text = getUnderlinedText(moduleSectionTitle)
        displayModuleSection()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun displayModuleSection() {
        binding.subModulesContainer.removeAllViews()
        for (moduleSubSection in moduleSection.subSections) {
            val subModuleBinding = ItemSubModuleBinding.inflate(
                layoutInflater,
                binding.subModulesContainer,
                false
            )
            subModuleBinding.name.text = moduleSubSection.name
            moduleSubSection.options?.let { options ->
                options.forEach { option ->
                    val optionBinding = ItemTextviewBinding.inflate(
                        layoutInflater,
                        subModuleBinding.optionsContainer,
                        false
                    )
                    optionBinding.root.text = getString(R.string.option, option)
                    subModuleBinding.optionsContainer.addView(optionBinding.root)
                }
            } ?: run {
                subModuleBinding.optionsContainer.visibility = GONE
            }

            moduleSubSection.detailedDescriptions?.let { detailedDescriptions ->
                detailedDescriptions.forEach { detailedDescription ->
                    val detailedDescriptionBinding = ItemDetailedDescriptionBinding.inflate(
                        layoutInflater,
                        subModuleBinding.detailedDescriptionsContainer,
                        false
                    )
                    detailedDescriptionBinding.name.text = detailedDescription.name
                    detailedDescription.descriptions.forEachIndexed { index, description ->
                        val descriptionBinding = ItemTextviewBinding.inflate(
                            layoutInflater,
                            detailedDescriptionBinding.descriptionsContainer,
                            false
                        )
                        descriptionBinding.root.text = "${index.plus(1)}. $description"
                        detailedDescriptionBinding.descriptionsContainer.addView(descriptionBinding.root)
                    }
                    subModuleBinding.detailedDescriptionsContainer.addView(
                        detailedDescriptionBinding.root
                    )
                }
            } ?: run {
                subModuleBinding.detailedDescriptionsContainer.visibility = GONE
            }

            moduleSubSection.descriptions?.let { descriptions ->
                descriptions.forEachIndexed { index, description ->
                    val descriptionBinding = ItemTextviewBinding.inflate(
                        layoutInflater,
                        subModuleBinding.descriptionsContainer,
                        false
                    )
                    descriptionBinding.root.text = "${index.plus(1)}. $description"
                    subModuleBinding.descriptionsContainer.addView(descriptionBinding.root)
                }
            } ?: run {
                subModuleBinding.descriptionsContainer.visibility = GONE
            }

            moduleSubSection.notes?.let { notes ->
                notes.forEach { note ->
                    val noteBinding = ItemNoteBinding.inflate(
                        layoutInflater,
                        subModuleBinding.notesContainer,
                        false
                    )
                    noteBinding.name.text = "${note.name}:"
                    noteBinding.description.text = note.description
                    subModuleBinding.notesContainer.addView(noteBinding.root)
                }
            } ?: run {
                subModuleBinding.notesContainer.visibility = GONE
            }

            binding.subModulesContainer.addView(subModuleBinding.root)
        }
    }

    companion object {
        const val ARG_MODULE_SECTION = "ARG_MODULE_SECTION"
        const val ARG_MODULE_SECTION_TITLE = "ARG_MODULE_SECTION_TITLE"
    }
}