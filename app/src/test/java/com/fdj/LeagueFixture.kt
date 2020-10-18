package com.fdj

import com.fdj.core.domain.League

class LeagueFixture {
    companion object {
        val ligue1 = Builder(
            id = "1234",
            name = "Ligue 1",
            alternative = "Première ligue",
            sport = "soccer"
        ).build()

        val ligue2 = Builder(
            id = "1234",
            name = "Ligue 2",
            alternative = "Deuxième ligue",
            sport = "soccer"
        ).build()

        val orderedList
            get() = listOf(ligue1, ligue2)

        val disorderedList
            get() = listOf(ligue2, ligue1)
    }

    data class Builder(
        val id: String = "",
        val name: String = "",
        val alternative: String = "",
        val sport: String
    ) {
        fun build() = League(
            id,
            name,
            alternative,
            sport
        )
    }
}