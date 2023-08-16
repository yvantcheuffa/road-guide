package com.southafrica.roadguide.ui.learner.learn.rules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.ActivityRoadRulesBinding

class RoadRulesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoadRulesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoadRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.road_rules)
    }
}