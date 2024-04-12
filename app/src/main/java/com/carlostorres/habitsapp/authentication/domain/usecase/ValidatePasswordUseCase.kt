package com.carlostorres.habitsapp.authentication.domain.usecase

class ValidatePasswordUseCase {

    operator fun invoke(password : String) : PasswordResult{

        if (password.length < 8){
            return PasswordResult.Invalid("Password need at least 8 characters")
        }

        if (!password.any { it.isLowerCase() }){
            return PasswordResult.Invalid("Password need at least one lower case letter")
        }

        if (!password.any { it.isUpperCase() }){
            return PasswordResult.Invalid("Password need at least one CAPITAL letter")
        }

        if (!password.any { it.isDigit() }){
            return PasswordResult.Invalid("Password need at least one number")
        }

        return PasswordResult.Valid

    }

}

sealed class PasswordResult(){
    object Valid : PasswordResult()
    data class Invalid(val errorMessage: String) : PasswordResult()
}