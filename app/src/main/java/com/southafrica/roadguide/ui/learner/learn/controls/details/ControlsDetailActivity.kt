package com.southafrica.roadguide.ui.learner.learn.controls.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.southafrica.roadguide.databinding.ActivityControlsDetailBinding
import com.southafrica.roadguide.databinding.ItemTextviewBinding
import com.southafrica.roadguide.model.VehicleControl

class ControlsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControlsDetailBinding
    private lateinit var vehicleControl: VehicleControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vehicleControl = intent.getSerializableExtra(ARG_VEHICLE_CONTROL) as VehicleControl
        binding = ActivityControlsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.title.text = vehicleControl.name
        val imageResId =
            resources.getIdentifier(vehicleControl.imageResName, "drawable", packageName)
        binding.image.setImageResource(imageResId)
        for ((index, description) in vehicleControl.descriptions.withIndex()) {
            val descriptionBinding = ItemTextviewBinding.inflate(
                layoutInflater,
                binding.descriptionsContainer,
                false
            )
            descriptionBinding.root.text = "${index.plus(1)}. $description"
            binding.descriptionsContainer.addView(descriptionBinding.root)
        }
    }

    companion object {
        const val ARG_VEHICLE_CONTROL = "ARG_VEHICLE_CONTROL"
    }
}