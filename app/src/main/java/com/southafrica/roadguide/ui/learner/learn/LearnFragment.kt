package com.southafrica.roadguide.ui.learner.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.southafrica.roadguide.databinding.FragmentLearnBinding
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
    }
}