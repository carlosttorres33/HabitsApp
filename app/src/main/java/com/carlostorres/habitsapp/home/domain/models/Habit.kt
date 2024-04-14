package com.carlostorres.habitsapp.home.domain.models

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

class Habit(
    val id: String,
    val name: String,
    val frequency : List<DayOfWeek>,
    val completedDates: List<LocalDate>,
    val remainder : LocalTime,
    val startDate : ZonedDateTime
) {
}