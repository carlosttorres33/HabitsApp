package com.carlostorres.habitsapp.home.data.remote.dto


data class HabitDto(
    val name: String,
    val remainder : Long,
    val startDate : Long,
    val frequency : List<Int>,
    val completedDates: List<Long>?
)
