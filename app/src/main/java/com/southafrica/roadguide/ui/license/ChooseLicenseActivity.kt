package com.southafrica.roadguide.ui.license

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.databinding.ActivityChooseLicenseBinding
import com.southafrica.roadguide.ui.driverfaq.DriverFaqActivity
import com.southafrica.roadguide.ui.learner.LearnerFaqActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseLicenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChooseLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            btnDrivers.setOnClickListener {
                startActivity(Intent(this@ChooseLicenseActivity, DriverFaqActivity::class.java))
                finish()
            }

            btnLearners.setOnClickListener {
                startActivity(Intent(this@ChooseLicenseActivity, LearnerFaqActivity::class.java))
                finish()
            }
        }
    }
}