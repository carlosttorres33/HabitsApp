package com.carlostorres.habitsapp.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.habitsapp.authentication.domain.usecase.PasswordResult
import com.carlostorres.habitsapp.authentication.domain.usecase.SignupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCases: SignupUseCases
) :ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    fun onEvent(events: SignupEvents){

        when(events){

            is SignupEvents.EmailChange ->{
                state = state.copy(
                    email = events.email
                )
            }
            is SignupEvents.PasswordChange ->{
                state = state.copy(
                    password = events.password
                )
            }
            is SignupEvents.LogIn ->{
                state = state.copy(
                    logIn = true
                )
            }
            is SignupEvents.SignUp ->{
                signUp()
            }

        }

    }

    private fun signUp(){

        state = state.copy(
            emailError = null,
            passwordError = null
        )

        if (!signupUseCases.validateEmailUseCase(state.email)){
            state = state.copy(emailError = "Invalid Email")
        }

        val passwordError = signupUseCases.validatePasswordUseCase(state.password)
        if (passwordError is PasswordResult.Invalid){
            state = state.copy(passwordError = passwordError.errorMessage)
        }

        if (state.emailError == null && state.passwordError == null){

            state = state.copy(isLoading = true)

            viewModelScope.launch {
                signupUseCases.signupWithEmailUseCase(state.email, state.password).onSuccess {
                    state = state.copy(
                        isSignedIn = true
                    )
                }.onFailure {
                    state = state.copy(
                        passwordError = it.message
                    )
                }
                state = state.copy(isLoading = false)
            }

        }

    }

}