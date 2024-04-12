package com.carlostorres.habitsapp.onboarding.domain.repository

interface OnboardingRepository {

    fun hasSeenOnboarding():Boolean

    fun completeOnboarding()

}