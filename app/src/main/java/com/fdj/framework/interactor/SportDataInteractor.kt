package com.fdj.framework.interactor

import com.fdj.core.data.SportDataSource
import com.fdj.core.data.SportRepository
import com.fdj.core.usecase.GetLeaguesUsecase
import com.fdj.core.usecase.GetTeamDetailsUsecase
import com.fdj.core.usecase.GetTeamsUsecase
import com.fdj.core.usecase.SortLeaguesByName
import javax.inject.Singleton

@Singleton
class SportDataInteractor(dataSource: SportDataSource) {
    val getLeagues: GetLeaguesUsecase
    val getTeams: GetTeamsUsecase
    val getDetailsTeams: GetTeamDetailsUsecase
    val sortLeaguesByName = SortLeaguesByName()

    init {
        val repository = SportRepository(dataSource)
        getLeagues = GetLeaguesUsecase(repository)
        getTeams = GetTeamsUsecase(repository)
        getDetailsTeams = GetTeamDetailsUsecase(repository)
    }
}