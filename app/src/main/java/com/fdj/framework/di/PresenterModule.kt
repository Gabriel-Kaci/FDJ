package com.fdj.framework.di

import androidx.lifecycle.ViewModel
import com.fdj.presentation.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresenterModule {
	@Binds
	@IntoMap
	@ViewModelKey(MainPresenter::class)
	abstract fun bindLoginViewModel(presenter: MainPresenter): ViewModel
}