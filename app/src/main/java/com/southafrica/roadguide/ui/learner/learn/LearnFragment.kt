package com.southafrica.roadguide.ui.learner.learn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.southafrica.roadguide.Utils.getUnderlinedText
import com.southafrica.roadguide.databinding.FragmentLearnBinding
import com.southafrica.roadguide.ui.learner.learn.controls.ControlsActivity
import com.southafrica.roadguide.ui.learner.learn.rules.RoadRulesActivity
import com.southafrica.roadguide.ui.learner.learn.signs.RoadSignActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnFragment : Fragment() {

    private lateinit var binding: FragmentLearnBinding
    private val viewModel: LearnViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            txvControls.text = getUnderlinedText(txvControls.text.toString())
            txvRoadRules.text = getUnderlinedText(txvRoadRules.text.toString())
            txvRoadSigns.text = getUnderlinedText(txvRoadSigns.text.toString())

            btnControls.setOnClickListener {
                startActivity(Intent(requireContext(), ControlsActivity::class.java))
            }
            btnRoadRules.setOnClickListener {
                startActivity(Intent(requireContext(), RoadRulesActivity::class.java))
            }
            btnRoadSign.setOnClickListener {
                startActivity(Intent(requireContext(), RoadSignActivity::class.java))
            }
        }
    }
}