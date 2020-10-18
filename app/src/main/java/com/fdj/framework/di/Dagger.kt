package com.fdj.framework.di

import com.fdj.framework.gataway.sportdb.SportDBDataSource
import com.fdj.presentation.details.TeamDetailsFragment
import com.fdj.presentation.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        InteractorModule::class,
        RetrofitModule::class,
        PresenterFactoryModule::class,
        PresenterModule::class
    ]
)
interface Component {
    fun inject(searchFragment: SearchFragment)
    fun inject(teamDetailsFragment: TeamDetailsFragment)

    fun inject(sportDBDataSource: SportDBDataSource)
}