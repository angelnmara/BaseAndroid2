package com.lamarrulla.mytiendita.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.lamarrulla.mytiendita.R
import com.lamarrulla.mytiendita.ui.ui.menu.MenuActivity
import com.lamarrulla.mytiendita.utils.Utils

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    private val utils = Utils(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils.fullScreen(window)
        setContentView(R.layout.activity_splash)
        delay()
    }

    private fun delay(){
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MenuActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}