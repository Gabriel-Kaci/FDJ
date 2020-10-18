package com.fdj.framework.di

import com.fdj.framework.gataway.sportdb.SportDBDataSource
import com.fdj.framework.interactor.SportDataInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule() {

    @Singleton
    @Provides
    fun getSportDataInteractor(): SportDataInteractor {
        return SportDataInteractor(SportDBDataSource())
    }
}