package com.fdj.framework.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterFactoryModule {
	@Binds
	abstract fun bindViewModelFactory(viewModelFactory: DaggerPresenterFactory): ViewModelProvider.Factory
}