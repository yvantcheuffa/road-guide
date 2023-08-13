package com.southafrica.roadguide.ui.driver

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.southafrica.roadguide.R
import java.util.Locale


class DriverPagerAdapter(
    fragmentManager: FragmentManager,
    val context: Context
) : FragmentPagerAdapter(fragmentManager) {

    private val titles = listOf(R.string.drivers_license, R.string.more)
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DriverLicenseFragment()
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
