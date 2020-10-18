package com.fdj.core.data

import com.fdj.core.domain.League
import com.fdj.core.domain.Team

interface SportDataSource {
    suspend fun getLeagues(): List<League>
    suspend fun getTeams(league: String): List<Team>
    suspend fun getTeamDetails(name: String): Team?
}