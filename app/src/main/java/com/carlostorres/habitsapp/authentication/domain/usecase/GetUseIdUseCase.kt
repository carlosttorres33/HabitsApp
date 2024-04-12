package com.carlostorres.habitsapp.authentication.domain.usecase

import com.carlostorres.habitsapp.authentication.domain.repository.AuthenticationRepository

class GetUseIdUseCase(private val repository: AuthenticationRepository) {

    operator fun invoke() : String? {
        return repository.getUserId()
    }

}