package com.fdj.framework.gataway.sportdb

import com.fdj.core.data.SportDataSource
import com.fdj.core.domain.League
import com.fdj.core.domain.Team
import com.fdj.framework.FDJ
import com.fdj.framework.gataway.sportdb.retrofit.Api
import javax.inject.Inject

class SportDBDataSource : SportDataSource {
    @Inject
    lateinit var api: Api

    init {
        FDJ.dagger.inject(this)
    }

    override suspend fun getLeagues(): List<League> = api.getLeagues().toLeagues()

    override suspend fun getTeams(league: String): List<Team> =
        api.getTeams(league).toTeams()

    override suspend fun getTeamDetails(name: String): Team? {
        val teams = api.getTeamDetails(name).toTeams()
        if (teams.isNotEmpty()) return teams[0]
        return null
    }
}