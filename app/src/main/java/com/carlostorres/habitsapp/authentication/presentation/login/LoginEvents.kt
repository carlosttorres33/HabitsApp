package com.carlostorres.habitsapp.authentication.presentation.login

sealed interface LoginEvents {

    data class EmailChange(val email: String): LoginEvents

    data class PasswordChange(val password: String): LoginEvents

    object Login : LoginEvents

    object SignUp : LoginEvents

}