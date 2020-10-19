package com.fdj.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fdj.R
import com.fdj.framework.FDJ

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.fragmentContainer).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    supportActionBar?.hide()
                }
                R.id.searchFragment -> {
                    supportActionBar?.show()
                }
                R.id.teamsDetailsFragment -> {
                    supportActionBar?.hide()
                }
            }
        }
    }
}