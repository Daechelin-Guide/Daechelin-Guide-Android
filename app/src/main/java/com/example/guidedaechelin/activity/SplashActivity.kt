package com.example.guidedaechelin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guidedaechelin.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try{

            Thread.sleep(2000)

        }catch (e : InterruptedException){

            e.printStackTrace()
        }

        val intent = Intent(this,MainActivity::class.java)

        startActivity(intent)

        finish()

        setContentView(R.layout.activity_splash)








    }
}