package dev.pascaline.workoutlog.repository

import dev.pascaline.workoutlog.api.ApiClient
import dev.pascaline.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
suspend fun fetchExerciseCategories(accessToken:String)= withContext(Dispatchers.IO){
    return@withContext apiClient.fetchExerciseCaegories(accessToken)
}
}