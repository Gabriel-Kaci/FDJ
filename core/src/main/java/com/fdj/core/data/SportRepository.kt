package com.fdj.core.data

class SportRepository(private val dataSource: SportDataSource) {
    suspend fun getLeagues() = dataSource.getLeagues()
    suspend fun getTeams(league: String) = dataSource.getTeams(league)
    suspend fun getTeamDetails(name: String) = dataSource.getTeamDetails(name)
}