package dev.pascaline.workoutlog.retrofit

import dev.pascaline.workoutlog.models.RegisterRequest
import dev.pascaline.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/regiter")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
}