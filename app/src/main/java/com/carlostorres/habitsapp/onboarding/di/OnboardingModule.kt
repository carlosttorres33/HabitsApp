package com.carlostorres.habitsapp.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.carlostorres.habitsapp.onboarding.data.repository.OnboardingRepoImpl
import com.carlostorres.habitsapp.onboarding.domain.repository.OnboardingRepository
import com.carlostorres.habitsapp.onboarding.domain.usecases.CompleteOnboardingUseCase
import com.carlostorres.habitsapp.onboarding.domain.usecases.HasSeenOnboardingUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(
        @ApplicationContext context : Context
    ):SharedPreferences{
        return context.getSharedPreferences("habits_onboarding_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository{
        return OnboardingRepoImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesHasSeenOnboardingUseCase( repository : OnboardingRepository): HasSeenOnboardingUsecase{
        return HasSeenOnboardingUsecase(repository)
    }

    @Provides
    @Singleton
    fun providesCompleteOnboardingUseCase( repository : OnboardingRepository): CompleteOnboardingUseCase{
        return CompleteOnboardingUseCase(repository)
    }

}