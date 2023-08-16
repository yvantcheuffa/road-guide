package com.southafrica.roadguide.ui.learner.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.southafrica.roadguide.Utils
import com.southafrica.roadguide.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: TestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btnJoinUs.setOnClickListener {
                startActivity(Utils.getJoinOnFacebookIntent(requireContext()))
            }
            btnBookTest.setOnClickListener { }
            btnControls.setOnClickListener { }
            btnK53Test.setOnClickListener { }
            btnSigns.setOnClickListener { }
            btnRoadRules.setOnClickListener { }
        }
    }
}