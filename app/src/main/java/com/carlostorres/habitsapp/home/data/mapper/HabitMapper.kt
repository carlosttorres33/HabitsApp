package com.carlostorres.habitsapp.home.data.mapper

import com.carlostorres.habitsapp.home.data.extencion.toStartOfDayTimestamp
import com.carlostorres.habitsapp.home.data.extencion.toTimeStamp
import com.carlostorres.habitsapp.home.data.extencion.toZoneDateTime
import com.carlostorres.habitsapp.home.data.local.entity.HabitEntity
import com.carlostorres.habitsapp.home.domain.models.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit{

    return Habit(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map{ DayOfWeek.of(it) },
        completedDates = this.completedDates.map { it.toZoneDateTime().toLocalDate() },
        remainder = this.remainder.toZoneDateTime().toLocalTime(),
        startDate = this.startDate.toZoneDateTime()
    )

}

fun Habit.toEntity(): HabitEntity{

    return HabitEntity(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map{ it.value },
        completedDates = this.completedDates.map { it.toZoneDateTime().toTimeStamp() },
        remainder = this.remainder.toZoneDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDayTimestamp()
    )

}

