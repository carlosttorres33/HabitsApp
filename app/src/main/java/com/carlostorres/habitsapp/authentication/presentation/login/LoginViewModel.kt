package com.carlostorres.habitsapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.habitsapp.authentication.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
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

        viewModelScope.launch {
            authenticationRepository.login(state.email, state.password).onSuccess {
                println("Chi")
            }.onFailure {
                val error = it.message
                println(error)
            }
        }

    }


}