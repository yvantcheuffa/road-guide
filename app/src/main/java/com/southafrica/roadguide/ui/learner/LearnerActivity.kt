package com.southafrica.roadguide.ui.learner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.databinding.ActivityLearnerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

    }
}