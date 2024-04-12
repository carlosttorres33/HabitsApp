package com.carlostorres.habitsapp.authentication.di

import com.carlostorres.habitsapp.authentication.data.matcher.EmailMtcherImpl
import com.carlostorres.habitsapp.authentication.data.repository.AuthenticationRepoImpl
import com.carlostorres.habitsapp.authentication.domain.matcher.EmailMatcher
import com.carlostorres.habitsapp.authentication.domain.repository.AuthenticationRepository
import com.carlostorres.habitsapp.authentication.domain.usecase.LoginUseCases
import com.carlostorres.habitsapp.authentication.domain.usecase.LoginWithEmailUseCase
import com.carlostorres.habitsapp.authentication.domain.usecase.ValidateEmailUseCase
import com.carlostorres.habitsapp.authentication.domain.usecase.ValidatePasswordUseCase
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

    @Provides
    @Singleton
    fun provideEmailMatcher():EmailMatcher{
        return EmailMtcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(repository: AuthenticationRepository, emailMatcher: EmailMatcher): LoginUseCases{
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            validateEmailUseCase =  ValidateEmailUseCase(emailMatcher)
        )
    }

}