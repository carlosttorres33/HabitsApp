package com.carlostorres.habitsapp.home.domain.home.usecase

import com.carlostorres.habitsapp.home.domain.models.Habit
import com.carlostorres.habitsapp.home.domain.repository.HomeRepository
import java.time.ZonedDateTime

class CompleteHabitUseCase(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(
        habit : Habit,
        date : ZonedDateTime
    ){

//        val newHabit = Habit(
//            id = habit.id,
//            name = habit.name,
//            frequency = habit.frequency,
//            completedDates = if ((habit.completedDates.contains(date.toLocalDate()))) habit.completedDates-date.toLocalDate() else habit.completedDates+date.toLocalDate(),
//            remainder = habit.remainder,
//            startDate = habit.startDate
//        )

        val newHabit = if (habit.completedDates.contains(date.toLocalDate())){
            Habit(
                id = habit.id,
                name = habit.name,
                frequency = habit.frequency,
                completedDates = habit.completedDates-date.toLocalDate(),
                remainder = habit.remainder,
                startDate = habit.startDate
            )
//            habit.copy(
//                completedDates = habit.completedDates - date.toLocalDate()
//            )
        }else{
            Habit(
                id = habit.id,
                name = habit.name,
                frequency = habit.frequency,
                completedDates = habit.completedDates+date.toLocalDate(),
                remainder = habit.remainder,
                startDate = habit.startDate
            )
        }

        repository.insertHabit(newHabit)

    }

}