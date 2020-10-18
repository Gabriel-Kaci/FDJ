package com.fdj.presentation

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(id: Int): NavController =
    (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController

// Copied from FragmentViewModelLazy.kt just for cosmetic
@MainThread
inline fun <reified VM : ViewModel> Fragment.presenter(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
) = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)

// Copied from ActivityViewModelLazy.kt just for cosmetic
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.presenter(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
}