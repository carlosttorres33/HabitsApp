package com.carlostorres.habitsapp.authentication.domain.usecase

import com.carlostorres.habitsapp.authentication.domain.repository.AuthenticationRepository

class LoginWithEmailUseCase(private val repository: AuthenticationRepository) {

    suspend operator fun invoke(
        email : String,
        password : String
    ): Result<Unit>{
        return repository.login(email, password)
    }

}