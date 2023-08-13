package com.southafrica.roadguide.ui.driverfaq

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.southafrica.roadguide.R
import com.southafrica.roadguide.databinding.ActivityDriverFaqBinding
import com.southafrica.roadguide.databinding.ItemFaqAnswerBinding
import com.southafrica.roadguide.databinding.ItemFaqBinding
import com.southafrica.roadguide.model.Faq
import com.southafrica.roadguide.ui.driver.DriverActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DriverFaqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriverFaqBinding
    private val viewModel: DriverFaqViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDriverFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        listenUiState()
    }

    private fun setupViews() {
        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, DriverActivity::class.java))
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
                val answerBinding = ItemFaqAnswerBinding.inflate(
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