package com.southafrica.roadguide.ui.learner.learn.signs

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.ActivityRoadSignBinding
import com.southafrica.roadguide.databinding.ItemRoadSignBinding

class RoadSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoadSignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoadSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.road_signs_title)

        setupViews()
    }

    private fun setupViews() {
        binding.signsContainer.removeAllViews()
        for (roadSign in getRoadSigns()) {
            with(ItemRoadSignBinding.inflate(layoutInflater, binding.signsContainer, false)) {
                title.text = getString(roadSign.titleResId)
                description.text = getString(roadSign.descriptionResId)
                image.setImageResource(roadSign.imageResId)
                binding.signsContainer.addView(root)
            }
        }
    }

    private fun getRoadSigns(): List<RoadSign> {
        return listOf(
            RoadSign(
                titleResId = R.string.regulatory_signs_title,
                descriptionResId = R.string.regulatory_signs_description,
                imageResId = R.drawable.ic_sign_regulatory
            ),
            RoadSign(
                titleResId = R.string.warning_signs_title,
                descriptionResId = R.string.warning_signs_description,
                imageResId = R.drawable.ic_sign_warning
            ),
            RoadSign(
                titleResId = R.string.guidance_signs_title,
                descriptionResId = R.string.guidance_signs_description,
                imageResId = R.drawable.ic_sign_guidance
            ),
            RoadSign(
                titleResId = R.string.temporary_signs_title,
                descriptionResId = R.string.temporary_signs_description,
                imageResId = R.drawable.ic_sign_temporary
            ),
            RoadSign(
                titleResId = R.string.road_markings_signs_title,
                descriptionResId = R.string.road_markings_signs_description,
                imageResId = R.drawable.ic_sign_road_markings
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}

data class RoadSign(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val imageResId: Int,
)