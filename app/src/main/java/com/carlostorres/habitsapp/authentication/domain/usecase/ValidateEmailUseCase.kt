package com.carlostorres.habitsapp.authentication.domain.usecase

import com.carlostorres.habitsapp.authentication.domain.matcher.EmailMatcher

class ValidateEmailUseCase(private val emailMatcher : EmailMatcher) {

    operator fun invoke(email : String) : Boolean{

        return emailMatcher.isValid(email)

    }

}