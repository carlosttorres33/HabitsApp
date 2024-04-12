package com.carlostorres.habitsapp.authentication.presentation.signup

interface SignupEvents {

    data class EmailChange(val email: String): SignupEvents

    data class PasswordChange(val password: String): SignupEvents

    object LogIn : SignupEvents

    object SignUp : SignupEvents

}