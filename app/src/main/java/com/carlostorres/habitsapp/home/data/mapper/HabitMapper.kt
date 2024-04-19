package com.carlostorres.habitsapp.home.data.mapper

import com.carlostorres.habitsapp.home.data.extencion.toStartOfDayTimestamp
import com.carlostorres.habitsapp.home.data.extencion.toTimeStamp
import com.carlostorres.habitsapp.home.data.extencion.toZoneDateTime
import com.carlostorres.habitsapp.home.data.local.entity.HabitEntity
import com.carlostorres.habitsapp.home.data.remote.dto.HabitDto
import com.carlostorres.habitsapp.home.data.remote.dto.HabitResponse
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

fun HabitResponse.toDomain(): List<Habit>{

    return this.entries.map {

        val id = it.key
        val dto = it.value
        Habit(
            id = id,
            name = dto.name,
            frequency = dto.frequency.map{ DayOfWeek.of(it) },
            completedDates = dto.completedDates?.map { it.toZoneDateTime().toLocalDate() } ?: emptyList(),
            remainder = dto.remainder.toZoneDateTime().toLocalTime(),
            startDate = dto.startDate.toZoneDateTime()
        )
    }

}

fun Habit.toDto(): HabitResponse {

    val dto = HabitDto(
        name = this.name,
        frequency = this.frequency.map{ it.value },
        completedDates = this.completedDates.map { it.toZoneDateTime().toTimeStamp() },
        remainder = this.remainder.toZoneDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDayTimestamp()
    )

    return mapOf(id to dto)

}
