package dev.pascaline.workoutlog.repository

import dev.pascaline.workoutlog.api.ApiClient
import dev.pascaline.workoutlog.api.ApiInterface
import dev.pascaline.workoutlog.models.LoginRequest
import dev.pascaline.workoutlog.models.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest:LoginRequest)= withContext(Dispatchers.IO){
        val response=apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest:RegisterRequest)= withContext(Dispatchers.IO){
        val response=apiClient.registerUser(registerRequest)
        return@withContext response
    }
}