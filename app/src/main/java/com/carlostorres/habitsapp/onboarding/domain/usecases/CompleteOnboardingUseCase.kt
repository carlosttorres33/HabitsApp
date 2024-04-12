package com.carlostorres.habitsapp.onboarding.domain.usecases

import com.carlostorres.habitsapp.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository : OnboardingRepository
) {

    operator fun invoke(){
        return repository.completeOnboarding()
    }

}