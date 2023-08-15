package com.southafrica.roadguide.ui.learner.faq

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.ActivityLearnerFaqBinding
import com.southafrica.roadguide.databinding.ItemFaqBinding
import com.southafrica.roadguide.databinding.ItemTextviewBinding
import com.southafrica.roadguide.model.Faq
import com.southafrica.roadguide.ui.learner.LearnerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LearnerFaqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnerFaqBinding
    private val viewModel: LearnerFaqViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLearnerFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        listenUiState()
    }

    private fun setupViews() {
        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, LearnerActivity::class.java))
            finish()
        }
    }

    private fun listenUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { uiState ->
                        uiState.faqs?.let(::displayFaqs)
                    }
                }
            }
        }
    }

    private fun displayFaqs(faqs: List<Faq>) {
        binding.faqContainer.removeAllViews()
        binding.title.text = getString(R.string.faq)
        for ((index, faq) in faqs.withIndex()) {
            val faqBinding = ItemFaqBinding.inflate(layoutInflater, binding.faqContainer, false)
            faqBinding.question.text = getString(R.string.faq_question, index + 1, faq.question)

            for (answer in faq.answers) {
                val answerBinding = ItemTextviewBinding.inflate(
                    layoutInflater,
                    faqBinding.answersContainer,
                    false
                )
                answerBinding.root.text = getString(R.string.faq_answer, answer)
                faqBinding.answersContainer.addView(answerBinding.root)
            }
            if (index == faqs.lastIndex) {
                faqBinding.divider.visibility = View.GONE
            }
            binding.faqContainer.addView(faqBinding.root)
        }
    }
}