package com.southafrica.roadguide.ui.driver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.southafrica.roadguide.databinding.FragmentDriverLicenseBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DriverLicenseFragment : Fragment() {
    private lateinit var binding: FragmentDriverLicenseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDriverLicenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnMotorcycle.setOnClickListener { }
        binding.btnLightMotorVehicle.setOnClickListener { }
        binding.btnHeavyMotorVehicle.setOnClickListener { }
        binding.btnJoinUs.setOnClickListener {
            val intent = try {
                requireContext().packageManager.getPackageInfo("com.facebook.katana", 0)
                Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<id_here>"))
            } catch (e: Exception) {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<user_name_here>"))
            }
            startActivity(intent)
        }
    }
}