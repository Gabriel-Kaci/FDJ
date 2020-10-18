package com.fdj.framework.gataway.sportdb

import com.fdj.core.domain.Team
import com.google.gson.annotations.SerializedName

data class TeamEntity(
    @SerializedName("idTeam")
    var idTeam: String?,
    @SerializedName("strTeam")
    var name: String?,
    @SerializedName("strTeamBadge")
    var pictureUrl: String?,
    @SerializedName("strTeamBanner")
    var bannerUrl: String?,
    @SerializedName("strDescriptionFR")
    var description: String?,
    @SerializedName("strDescriptionEN")
    var descriptionEn: String?,
    @SerializedName("strCountry")
    var country: String?,
    @SerializedName("strLeague")
    var league: String?
) {
    fun toTeam(): Team = Team(
        idTeam ?: "",
        name ?: "",
        pictureUrl ?: "",
        bannerUrl ?: "",
        description ?: descriptionEn ?: "",
        country ?: "",
        league ?: ""
    )
}