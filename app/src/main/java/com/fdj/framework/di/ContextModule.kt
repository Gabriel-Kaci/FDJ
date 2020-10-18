package com.fdj.framework.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {
    @Provides
    fun provideApplication(): Application = application

    @Provides
    fun provideContext(): Context = application.applicationContext
}