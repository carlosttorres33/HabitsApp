package com.carlostorres.habitsapp.home.presentation.home

import com.carlostorres.habitsapp.home.domain.models.Habit
import java.time.ZonedDateTime

sealed interface HomeEvent {

    data class ChangeDate(val date: ZonedDateTime) : HomeEvent

    data class CompleteHabits(val habit: Habit): HomeEvent

}