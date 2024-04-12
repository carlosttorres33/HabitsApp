package com.carlostorres.habitsapp.authentication.data.repository

import com.carlostorres.habitsapp.authentication.domain.repository.AuthenticationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthenticationRepoImpl: AuthenticationRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {

        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }

    }
}