package com.southafrica.roadguide.ui.learner

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.R
import com.southafrica.roadguide.Utils
import com.southafrica.roadguide.databinding.ActivityLearnerBinding
import com.southafrica.roadguide.ui.learner.faq.LearnerFaqActivity
import com.southafrica.roadguide.ui.license.ChooseLicenseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.elevation = 0f
        setupViews()
    }

    private fun setupViews() {
        val adapter = LearnerPagerAdapter(supportFragmentManager, this)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.choose_license_type -> {
                startActivity(Intent(this, ChooseLicenseActivity::class.java))
                finish()
            }

            R.id.faq -> startActivity(Intent(this, LearnerFaqActivity::class.java))
            R.id.share_via -> startActivity(Utils.getShareIntent(this))
            R.id.rate_app -> rateApp()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun rateApp() {
        val intent = Utils.getAppRatingIntent(this)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.error_rating, Toast.LENGTH_SHORT).show()
        }
    }
}