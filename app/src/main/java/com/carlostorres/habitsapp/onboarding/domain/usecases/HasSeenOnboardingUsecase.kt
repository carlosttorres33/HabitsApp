package com.carlostorres.habitsapp.onboarding.domain.usecases

import com.carlostorres.habitsapp.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUsecase(
    private val repository : OnboardingRepository
) {

    operator fun invoke(): Boolean {

        return repository.hasSeenOnboarding()

    }

}