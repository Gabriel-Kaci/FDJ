package com.fdj.framework.gataway.sportdb.retrofit

import com.fdj.framework.gataway.sportdb.LeaguesEntity
import com.fdj.framework.gataway.sportdb.TeamsEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    companion object {
        const val URL = "https://www.thesportsdb.com/api/v1/json/1/"
    }

    @GET("all_leagues.php")
    suspend fun getLeagues(): LeaguesEntity

    @GET("search_all_teams.php?")
    suspend fun getTeams(@Query("l") league: String): TeamsEntity

    @GET("searchteams.php?")
    suspend fun getTeamDetails(@Query("t") teamName: String): TeamsEntity
}