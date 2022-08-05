package com.example.incidencias_sem.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.incidencias_sem.R
import com.example.incidencias_sem.databinding.ActivitySignBinding

class SignActivity:AppCompatActivity() {

    private lateinit var binding:ActivitySignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }

}