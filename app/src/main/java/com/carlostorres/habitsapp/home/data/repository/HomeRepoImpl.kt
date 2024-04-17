package com.carlostorres.habitsapp.home.data.repository

import com.carlostorres.habitsapp.home.data.extencion.toStartOfDayTimestamp
import com.carlostorres.habitsapp.home.data.local.HabitDao
import com.carlostorres.habitsapp.home.data.mapper.toDomain
import com.carlostorres.habitsapp.home.data.mapper.toEntity
import com.carlostorres.habitsapp.home.domain.models.Habit
import com.carlostorres.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

class HomeRepoImpl(
    private val dao: HabitDao
): HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {

        return dao.getHabitsForSelectedDate(
            date.toStartOfDayTimestamp()
        ).map {
            it.map {
                it.toDomain()
            }
        }

    }

    override suspend fun insertHabit(habit: Habit) {

        dao.insertHabit(habit.toEntity())

    }

    override suspend fun getHabitById(id: String): Habit {

        return dao.getHabitById(id).toDomain()

    }

}