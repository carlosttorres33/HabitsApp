package com.carlostorres.habitsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.carlostorres.habitsapp.onboarding.domain.usecases.HasSeenOnboardingUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val hasSeenOnboardingUsecase: HasSeenOnboardingUsecase
) : ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUsecase())
        private set

}