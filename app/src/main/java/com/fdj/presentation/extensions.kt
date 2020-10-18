package com.fdj.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(id: Int): NavController =
    (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController