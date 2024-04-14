package com.carlostorres.habitsapp.home.domain.home.usecase

import com.carlostorres.habitsapp.authentication.domain.usecase.GetUseIdUseCase

data class HomeUseCases(
    val completeHabitUseCase : CompleteHabitUseCase,
    val getHabitsForDateUseCase : GetHabitsForDateUseCase
)
