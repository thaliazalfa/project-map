package com.example.projectmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.belajarrecyclerview.R
import java.util.Timer
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer().schedule(3000) {
            goToSignInPage()
        }
    }

    private fun goToSignInPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}