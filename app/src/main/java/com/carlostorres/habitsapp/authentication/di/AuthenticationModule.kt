package com.carlostorres.habitsapp.authentication.di

import com.carlostorres.habitsapp.authentication.data.repository.AuthenticationRepoImpl
import com.carlostorres.habitsapp.authentication.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository() : AuthenticationRepository{
        return AuthenticationRepoImpl()
    }

}