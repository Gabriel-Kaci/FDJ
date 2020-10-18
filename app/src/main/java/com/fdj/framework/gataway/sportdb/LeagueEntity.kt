package com.fdj.framework.gataway.sportdb

import com.fdj.core.domain.League
import com.google.gson.annotations.SerializedName

class LeagueEntity(
    @SerializedName("idTeam")
    var id: String?,
    @SerializedName("strLeague")
    var name: String?,
    @SerializedName("strSport")
    var sport: String?,
    @SerializedName("strLeagueAlternate")
    var alternative: String?,
) {
    fun toLeague() = League(
        id ?: "",
        name ?: "",
        alternative ?: "",
        sport ?: ""
    )
}