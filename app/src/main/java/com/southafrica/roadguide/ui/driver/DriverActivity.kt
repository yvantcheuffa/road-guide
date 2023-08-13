package com.southafrica.roadguide.ui.driver

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.ActivityDriverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDriverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.elevation = 0f
        setupViews()
    }

    private fun setupViews() {
        val adapter = DriverPagerAdapter(supportFragmentManager, this)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}