package com.fdj.framework

import android.app.Application
import com.fdj.framework.di.Component
import com.fdj.framework.di.DaggerComponent

class FDJ : Application() {

    companion object {
        lateinit var dagger: Component
    }

    override fun onCreate() {
        super.onCreate()

        dagger = DaggerComponent.builder()
            .build()
    }
}
