package com.my.sapiaassignment.uilayer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.my.sapiaassignment.R
import com.my.sapiaassignment.utility.TimeUtility

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PetsMainActivity::class.java)
            startActivity(intent)
            finish()
        }, TimeUtility.launchTime) // 3000 is the delayed time in milliseconds.
    }
}