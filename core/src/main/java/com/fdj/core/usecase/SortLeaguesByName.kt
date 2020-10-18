package com.fdj.core.usecase

import com.fdj.core.domain.League

class SortLeaguesByName {
    operator fun invoke(teams: List<League>, name: String): List<String> {
        return teams.map { it.name }.filter { it.contains(name, true) }.sortedBy { it }
    }
}