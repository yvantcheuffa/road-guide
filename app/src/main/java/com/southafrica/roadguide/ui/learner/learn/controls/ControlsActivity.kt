package com.southafrica.roadguide.ui.learner.learn.controls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.ActivityControlsBinding
import com.southafrica.roadguide.databinding.ItemControlsBinding
import com.southafrica.roadguide.model.VehicleControl
import com.southafrica.roadguide.ui.learner.learn.controls.details.ControlsDetailActivity
import com.southafrica.roadguide.ui.learner.learn.controls.details.ControlsDetailActivity.Companion.ARG_VEHICLE_CONTROL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ControlsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControlsBinding
    private val viewModel: ControlsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityControlsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.controls)

        listenUiState()
    }

    private fun listenUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { uiState ->
                        uiState.vehicleControls?.let(::displayControls)
                    }
                }
            }
        }
    }

    private fun displayControls(vehicleControls: List<VehicleControl>) {
        binding.controlsContainer.removeAllViews()
        for (vehicleControl in vehicleControls) {
            with(ItemControlsBinding.inflate(layoutInflater, binding.controlsContainer, false)) {
                val iconResId = resources.getIdentifier(vehicleControl.iconResName, "drawable", packageName)
                icon.setImageResource(iconResId)
                binding.controlsContainer.addView(root)
                root.setOnClickListener {
                    val intent = Intent(this@ControlsActivity, ControlsDetailActivity::class.java)
                    intent.putExtra(ARG_VEHICLE_CONTROL, vehicleControl)
                    startActivity(intent)
                }
            }
        }
    }

    private fun displayControls() {

    }
}