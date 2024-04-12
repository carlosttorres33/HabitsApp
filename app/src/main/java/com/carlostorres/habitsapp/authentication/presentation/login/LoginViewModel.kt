package com.carlostorres.habitsapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.habitsapp.authentication.domain.usecase.LoginUseCases
import com.carlostorres.habitsapp.authentication.domain.usecase.PasswordResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event : LoginEvents){

        when (event){
            LoginEvents.Login -> {
                login()
            }
            is LoginEvents.EmailChange -> {
                state = state.copy(
                    email = event.email
                )
            }
            is LoginEvents.PasswordChange -> {
                state = state.copy(
                    password = event.password
                )
            }
            LoginEvents.SignUp -> {
                state = state.copy(signUp = true)
            }
        }

    }

    private fun login(){

        state = state.copy(
            emailError = null,
            passwordError = null
        )

        if (!loginUseCases.validateEmailUseCase(state.email)){
            state = state.copy(emailError = "Invalid Email")
        }

        val passwordError = loginUseCases.validatePasswordUseCase(state.password)
        if (passwordError is PasswordResult.Invalid){
            state = state.copy(passwordError = passwordError.errorMessage)
        }

        if (state.emailError == null && state.passwordError == null){

            state = state.copy(isLoading = true)

            viewModelScope.launch {
                loginUseCases.loginWithEmailUseCase(state.email, state.password).onSuccess {
                    state = state.copy(
                        isLoggedIn = true
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