package com.carlostorres.habitsapp.home.presentation.home

import com.carlostorres.habitsapp.home.domain.models.Habit
import java.time.ZonedDateTime

data class HomeState(
    val currentDate: ZonedDateTime= ZonedDateTime.now(),
    val selectedDate : ZonedDateTime = ZonedDateTime.now(),
    val habits : List<Habit> = emptyList()
)
