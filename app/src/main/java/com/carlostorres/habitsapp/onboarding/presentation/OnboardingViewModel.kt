package com.carlostorres.habitsapp.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.carlostorres.habitsapp.onboarding.domain.usecases.CompleteOnboardingUseCase
import com.carlostorres.habitsapp.onboarding.domain.usecases.HasSeenOnboardingUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val hasSeenOnboardingUsecase: HasSeenOnboardingUsecase,
    private val hasCompleteOnboardingUseCase: CompleteOnboardingUseCase
) : ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUsecase())
        private set

    fun completeOnboarding(){
        hasCompleteOnboardingUseCase()
        hasSeenOnboarding = true
    }

}