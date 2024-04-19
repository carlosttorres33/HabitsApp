package com.carlostorres.habitsapp.home.data.remote

import com.carlostorres.habitsapp.home.data.remote.dto.HabitDto
import com.carlostorres.habitsapp.home.data.remote.dto.HabitResponse
import retrofit2.http.GET
import retrofit2.http.PATCH

interface HomeAPI {

    companion object{

        const val BASE_URL = "https://dailyhabitsapp-5f784-default-rtdb.firebaseio.com/"

    }

    @GET("habits.json")
    suspend fun getAllHabits():HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(habit: HabitDto)

}