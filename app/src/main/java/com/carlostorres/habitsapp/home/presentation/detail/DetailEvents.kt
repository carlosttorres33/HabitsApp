package com.carlostorres.habitsapp.home.presentation.detail

import java.time.DayOfWeek
import java.time.LocalTime

sealed interface DetailEvents{

    data class RemainderCheck(val time : LocalTime) : DetailEvents

    data class FrequencyChange(val dayOfWeek: DayOfWeek) : DetailEvents

    data class NameChange(val name: String) : DetailEvents

    object HabitSave : DetailEvents

}