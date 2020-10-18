package com.fdj.core.usecase

import com.fdj.core.data.SportRepository

class GetTeamDetailsUsecase(private val repository: SportRepository) {
    suspend operator fun invoke(name: String) = repository.getTeamDetails(name)
}