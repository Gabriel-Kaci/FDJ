package com.fdj.core.usecase

import com.fdj.core.data.SportRepository

class GetLeaguesUsecase(private val repository: SportRepository) {
    suspend operator fun invoke() = repository.getLeagues()
}