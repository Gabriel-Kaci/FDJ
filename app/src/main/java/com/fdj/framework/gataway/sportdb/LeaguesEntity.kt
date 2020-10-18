package com.fdj.framework.gataway.sportdb

import com.fdj.core.domain.League
import com.google.gson.annotations.SerializedName

class LeaguesEntity(
    @SerializedName("leagues")
    var leagues: List<LeagueEntity>?
) {
    fun toLeagues(): List<League> = leagues?.map { it.toLeague() } ?: listOf()
}