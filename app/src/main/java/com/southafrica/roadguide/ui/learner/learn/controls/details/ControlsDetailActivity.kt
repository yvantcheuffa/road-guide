package com.southafrica.roadguide.ui.learner.learn.controls.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = vehicleControl.name
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_VEHICLE_CONTROL = "ARG_VEHICLE_CONTROL"
    }
}