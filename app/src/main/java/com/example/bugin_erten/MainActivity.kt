package com.example.bugin_erten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bugin_erten.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager
            .findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment)
            .navController

        setupWithNavController(binding.bottomNavigationView, navController)

//        val viewPager = findViewById<ViewPager>(R.id.viewPager)
//        viewPager.adapter = PageAdapter(supportFragmentManager)
//
//        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
//        tabLayout.setupWithViewPager(viewPager)
    }
}