package com.southafrica.roadguide.ui.driver.license

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.southafrica.roadguide.Utils.getUnderlinedText
import com.southafrica.roadguide.databinding.ItemDriverLicenseBinding
import com.southafrica.roadguide.model.DriverLicense


class DriverLicenseAdapter(
    context: Context,
    driverLicences: List<DriverLicense>,
    private val listener: OnDriverLicenceClickListener
) : ArrayAdapter<DriverLicense>(context, 0, driverLicences) {
    interface OnDriverLicenceClickListener {
        fun onClick(driverLicense: DriverLicense)
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemDriverLicenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val driverLicense = getItem(position)!!
        return with(binding) {
            name.text = getUnderlinedText(driverLicense.name)
            title.text = driverLicense.name
            description.text = driverLicense.description
            val iconResId = parent.resources.getIdentifier(
                driverLicense.iconResName,
                "drawable",
                parent.context.packageName
            )
            icon.setImageResource(iconResId)
            btnViewModules.setOnClickListener { listener.onClick(driverLicense) }
            root
        }
    }
}

