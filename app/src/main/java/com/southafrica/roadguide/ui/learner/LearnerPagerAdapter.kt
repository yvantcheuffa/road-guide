package com.southafrica.roadguide.ui.learner

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.southafrica.roadguide.R
import com.southafrica.roadguide.ui.MoreFragment
import com.southafrica.roadguide.ui.driver.license.DriverLicenseFragment
import com.southafrica.roadguide.ui.learner.learn.LearnFragment
import com.southafrica.roadguide.ui.learner.progress.ProgressFragment
import com.southafrica.roadguide.ui.learner.test.TestFragment
import java.util.Locale


class LearnerPagerAdapter(
    fragmentManager: FragmentManager,
    val context: Context
) : FragmentPagerAdapter(fragmentManager) {

    private val titles = listOf(R.string.test, R.string.learn, R.string.progress, R.string.more)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TestFragment()
            1 -> LearnFragment()
            2 -> ProgressFragment()
            else -> MoreFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.getString(titles[position]).uppercase(Locale.ENGLISH)
    }
}
