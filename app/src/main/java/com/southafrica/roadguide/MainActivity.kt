package com.southafrica.roadguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.southafrica.roadguide.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

private const val SPLASH_TIME_MILLIS = 2_000L

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var closeSplashScreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSplashScreen()
    }

    private fun setupSplashScreen() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (closeSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            },
        )
    }

    private fun setTimer() {
        Timer().schedule(
            object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        closeSplashScreen = true
                    }
                }
            },
            SPLASH_TIME_MILLIS,
        )
    }
}