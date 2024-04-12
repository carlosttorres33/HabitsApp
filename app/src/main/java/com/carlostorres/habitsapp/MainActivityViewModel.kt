package com.carlostorres.habitsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.carlostorres.habitsapp.authentication.domain.usecase.GetUseIdUseCase
import com.carlostorres.habitsapp.onboarding.domain.usecases.HasSeenOnboardingUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val hasSeenOnboardingUsecase: HasSeenOnboardingUsecase,
    private val getUserIdUseCase: GetUseIdUseCase
) : ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUsecase())
        private set

    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
        private set

}