package com.golgeciarif.newsapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.golgeciarif.newsapp.R
import com.golgeciarif.newsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         navView = binding.bottomNavigation
        setupNavigation()

    }



    private fun setupNavigation(){
        val navController = findNavController(R.id.mainHostFragment)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ controller, destination,arguments ->
            when(destination.id){
                R.id.homeFragment2-> navView.visibility= View.VISIBLE
                R.id.newsDetailFragment-> navView.visibility= View.GONE
            }

        }
    }
}