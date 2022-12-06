package com.example.bugin_erten.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bugin_erten.databinding.ActivityMainBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.bugin_erten.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.i("onCreate called")
        navController = (supportFragmentManager
            .findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment)
            .navController

        setupWithNavController(binding.bottomNavigationView, navController)

    }
}