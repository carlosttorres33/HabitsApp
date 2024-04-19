package com.carlostorres.habitsapp.home.data.repository

import com.carlostorres.habitsapp.home.data.extencion.toStartOfDayTimestamp
import com.carlostorres.habitsapp.home.data.local.HabitDao
import com.carlostorres.habitsapp.home.data.mapper.toDomain
import com.carlostorres.habitsapp.home.data.mapper.toDto
import com.carlostorres.habitsapp.home.data.mapper.toEntity
import com.carlostorres.habitsapp.home.data.remote.HomeAPI
import com.carlostorres.habitsapp.home.data.remote.utils.resultOf
import com.carlostorres.habitsapp.home.domain.models.Habit
import com.carlostorres.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime

class HomeRepoImpl(
    private val dao: HabitDao,
    private val api: HomeAPI
): HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {

        val localFlow = dao.getHabitsForSelectedDate(
            date.toStartOfDayTimestamp()
        ).map {
            it.map {
                it.toDomain()
            }
        }

        val apiFlow = getHabitsFromApi()

        return localFlow.combine(apiFlow){ db, api ->
            db
        }

    }

    private fun getHabitsFromApi(): Flow<List<Habit>> {

        return flow {

            resultOf {
                val habits = api.getAllHabits().toDomain()
                insertHabits(habits)
            }
            emit(emptyList<Habit>())
        }.onStart {
            emit(emptyList())
        }

    }

    override suspend fun insertHabit(habit: Habit) {

        dao.insertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(habit.toDto())
        }


    }

    private suspend fun insertHabits(habits: List<Habit>) {

        dao.insertHabits(habits.map { it.toEntity() })

    }

    override suspend fun getHabitById(id: String): Habit {

        return dao.getHabitById(id).toDomain()

    }

}