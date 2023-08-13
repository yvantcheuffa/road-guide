package com.southafrica.roadguide.ui.learner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.databinding.ActivityLearnerFaqBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnerFaqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnerFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnerFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {}
}