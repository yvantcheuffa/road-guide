package com.southafrica.roadguide.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.southafrica.roadguide.ui.license.ChooseLicenseActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(this, ChooseLicenseActivity::class.java))
            finish()
        }, 3000)
    }
}
