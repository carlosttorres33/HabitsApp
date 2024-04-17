package com.carlostorres.habitsapp.home.domain.detail.usecase

import com.carlostorres.habitsapp.home.domain.models.Habit
import com.carlostorres.habitsapp.home.domain.repository.HomeRepository
import java.time.ZonedDateTime

class InsertHabitUseCase(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(
        habit : Habit
    ){

        repository.insertHabit(habit)

    }

}