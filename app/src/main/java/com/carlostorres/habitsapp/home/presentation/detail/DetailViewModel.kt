package com.carlostorres.habitsapp.home.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.habitsapp.home.domain.detail.usecase.DetailUseCases
import com.carlostorres.habitsapp.home.domain.models.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init {
        val id = savedStateHandle.get<String?>("habitId")
        if (id != null){
            viewModelScope.launch {
                val habit = detailUseCases.getHabitByIdUseCase(id)
                state = state.copy(
                    id = habit.id,
                    habitName = habit.name,
                    frequency = habit.frequency,
                    reminder = habit.remainder,
                    completedDates = habit.completedDates,
                    startDate = habit.startDate
                )
                println()
            }
        }
    }

    fun onEvent(event : DetailEvents){

        when(event){
            is DetailEvents.FrequencyChange -> {
                val frequency = if (state.frequency.contains(event.dayOfWeek)){
                    state.frequency - event.dayOfWeek
                } else {
                    state.frequency + event.dayOfWeek
                }
                state = state.copy(
                    frequency = frequency
                )
            }
            DetailEvents.HabitSave -> {

                viewModelScope.launch {
                    val habit = Habit(
                        id = state.id ?: UUID.randomUUID().toString(),
                        name =  state.habitName,
                        frequency = state.frequency,
                        completedDates = state.completedDates,
                        remainder = state.reminder,
                        startDate =  state.startDate
                    )
                    detailUseCases.insertHabitUseCase(habit)
                }

                state=state.copy(
                    isSave = true
                )

            }
            is DetailEvents.NameChange -> {
                state = state.copy(
                    habitName = event.name
                )
            }
            is DetailEvents.RemainderCheck ->{
                state = state.copy(
                    reminder = event.time
                )
            }
        }

    }

}