package com.fdj.framework.gataway.sportdb

import android.util.Log
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

    override suspend fun getLeagues(): List<League>? {
        return try {
            api.getLeagues().toLeagues()
        } catch (e: Exception) {
            Log.e("SportDBDataSource", "getLeagues error :", e)
            null
        }
    }

    override suspend fun getTeams(league: String): List<Team>? {
        return try {
            api.getTeams(league).toTeams()
        } catch (e: Exception) {
            Log.e("SportDBDataSource", "getTeams error :", e)
            null
        }
    }

    override suspend fun getTeamDetails(name: String): Team? {
        return try {
            val teams = api.getTeamDetails(name).toTeams()
            if (teams.isNotEmpty()) return teams[0]
            return null
        } catch (e: Exception) {
            Log.e("SportDBDataSource", "getTeamDetails error :", e)
            null
        }
    }
}