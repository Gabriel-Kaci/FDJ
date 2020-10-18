package com.fdj.core.usecase

import com.fdj.core.data.SportRepository

class GetTeamsUsecase(private val repository: SportRepository) {
    suspend operator fun invoke(league: String) = repository.getTeams(league)
}