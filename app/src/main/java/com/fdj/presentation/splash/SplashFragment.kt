package com.fdj.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.fdj.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(), CoroutineScope by MainScope() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, b: Bundle?): View? =
        i.inflate(R.layout.fragment_splash, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {
            delay(1000)
            val options = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
            findNavController().navigate(R.id.searchFragment, null, options)
        }
    }
}