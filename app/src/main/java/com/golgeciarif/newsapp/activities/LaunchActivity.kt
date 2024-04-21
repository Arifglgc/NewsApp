package com.golgeciarif.newsapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.golgeciarif.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    //val viewModel by lazy {
       // val firebaseDb = FirabaseDb()
        //val viewModelFactory = ViewModelProviderFactory(firebaseDb)
        //ViewModelProvider(this,viewModelFactory)[LaunchViewModel::class.java]
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        supportActionBar?.hide()

    }
}