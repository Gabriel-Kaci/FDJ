package com.fdj.core.domain

import androidx.annotation.Keep

@Keep
data class League(
    val id: String,
    val name: String,
    val alternativeName: String,
    val sport: String
)