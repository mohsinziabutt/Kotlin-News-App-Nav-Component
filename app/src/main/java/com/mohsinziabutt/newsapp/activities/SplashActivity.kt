package com.mohsinziabutt.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.mohsinziabutt.newsapp.R
import com.mohsinziabutt.newsapp.prefmanager.SharedPrefManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, NewsActivityMain::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        else
        {
            var kotlinLogo = findViewById<ImageView>(R.id.kotlin_logo)
            kotlinLogo.animate().alpha(1f).rotation(720f).scaleXBy(1f).scaleYBy(1f).setDuration(1500)

            Handler().postDelayed(
                {
                    val intent = Intent(applicationContext, LoginRegistrationActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                },
                2000 // value in milliseconds
            )
        }
    }
}