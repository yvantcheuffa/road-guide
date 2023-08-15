package com.southafrica.roadguide.ui.driver.license

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.FragmentDriverLicenseBinding
import com.southafrica.roadguide.model.DriverLicense
import com.southafrica.roadguide.ui.driver.module.ModuleActivity
import com.southafrica.roadguide.ui.driver.module.ModuleActivity.Companion.ARG_DRIVER_LICENSE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DriverLicenseFragment : Fragment(), DriverLicenseAdapter.OnDriverLicenceClickListener {
    private lateinit var binding: FragmentDriverLicenseBinding
    private val viewModel: DriverLicenceViewModel by viewModels()
    private lateinit var mProgressDialog: ProgressDialog

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
        listenUpdates()
    }

    private fun listenUpdates() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { uiState ->
                        with(uiState) {
                            toggleProgressDialog(uiState.isLoading)
                            driversLicenses?.let(::populateListView)
                            errorResId?.let {
                                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun toggleProgressDialog(isLoading: Boolean) {
        if (isLoading) {
            if (!mProgressDialog.isShowing) {
                mProgressDialog.show()
            }
        } else {
            if (mProgressDialog.isShowing) {
                mProgressDialog.dismiss()
            }
        }
    }

    private fun setupListeners() {
        mProgressDialog = ProgressDialog(requireContext())
        mProgressDialog.setTitle(getString(R.string.loading_licenses))
        mProgressDialog.setMessage(getString(R.string.waiting_message))
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

    private fun populateListView(driverLicenses: List<DriverLicense>) {
        val adapter = DriverLicenseAdapter(requireContext(), driverLicenses, this)
        binding.licenseListView.adapter = adapter
    }

    override fun onClick(driverLicense: DriverLicense) {
        val intent = Intent(requireContext(), ModuleActivity::class.java)
        intent.putExtra(ARG_DRIVER_LICENSE, driverLicense)
        startActivity(intent)
    }
}
