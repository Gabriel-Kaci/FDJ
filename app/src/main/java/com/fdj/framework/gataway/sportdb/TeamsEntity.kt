package com.fdj.framework.gataway.sportdb

import com.fdj.core.domain.Team
import com.google.gson.annotations.SerializedName

class TeamsEntity(
    @SerializedName("teams")
    var teams: List<TeamEntity>?
) {
    fun toTeams(): List<Team> = teams?.map { it.toTeam() } ?: listOf()
}