package com.carlostorres.habitsapp.home.di

import com.carlostorres.habitsapp.home.data.repository.HomeRepoImpl
import com.carlostorres.habitsapp.home.domain.home.usecase.CompleteHabitUseCase
import com.carlostorres.habitsapp.home.domain.home.usecase.GetHabitsForDateUseCase
import com.carlostorres.habitsapp.home.domain.home.usecase.HomeUseCases
import com.carlostorres.habitsapp.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun providesHomeUseCases(
        repository: HomeRepository
    ): HomeUseCases{
        
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = GetHabitsForDateUseCase(repository)
        )
        
    }

    @Provides
    @Singleton
    fun providesHomeRepository(

    ): HomeRepository{

        return HomeRepoImpl()

    }

}