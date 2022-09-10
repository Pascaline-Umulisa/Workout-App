package dev.pascaline.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pascaline.workoutlog.models.LoginRequest
import dev.pascaline.workoutlog.models.LoginResponse
import dev.pascaline.workoutlog.models.RegisterRequest
import dev.pascaline.workoutlog.models.RegisterResponse
import dev.pascaline.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository=UserRepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErroLiveData=MutableLiveData<String?>()

    var registerResponseLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData=MutableLiveData<String?>()


    fun loginUser(loginRequest:LoginRequest){
        viewModelScope.launch {
            val response=userRepository.loginUser(loginRequest)

            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error=response.errorBody()?.string()
                loginErroLiveData.postValue(error)
            }
        }
    }

    fun registerUser(registerRequest:RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)

            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            }
            else{
                val error=response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
}