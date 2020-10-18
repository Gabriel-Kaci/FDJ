package com.fdj

import com.fdj.core.usecase.SortLeaguesByName
import org.junit.Test

class SportDataInteractorTest {
    @Test
    fun `GIVEN leagues WHEN sort them THEN leagues are sorted`() {
        // Given
        val sortLeagues = SortLeaguesByName()
        val leagues = LeagueFixture.disorderedList

        // When
        val sorted = sortLeagues(leagues, "")

        // Then
        for (i in 1 until leagues.size) {
            assert(sorted[i] > sorted[i - 1])
        }
    }
}