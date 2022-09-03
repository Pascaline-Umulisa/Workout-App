package dev.pascaline.workoutlog.retrofit

import dev.pascaline.workoutlog.models.LoginRequest
import dev.pascaline.workoutlog.models.LoginResponse
import dev.pascaline.workoutlog.models.RegisterRequest
import dev.pascaline.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>
}