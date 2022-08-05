package com.example.incidencias_sem.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.incidencias_sem.R
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.databinding.ActivityMainBinding

class Init : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        SplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }

    fun SplashScreen() {//Llamada al Splash Screen de inicio de la aplicación
        Thread.sleep(2000)// Delay 2 second
        setTheme(R.style.SplashTheme)
        if (App.isLogued()){
            startActivity(Intent(this,Navigation_Drawer::class.java))
            finish()
        }else{
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }
}





