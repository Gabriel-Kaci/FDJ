package com.fdj.core.domain

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Team(
    val idTeam: String = "",
    val name: String = "",
    val pictureUrl: String = "",
    var bannerUrl: String = "",
    var description: String = "",
    var country: String = "",
    var league: String = ""
) : Serializable