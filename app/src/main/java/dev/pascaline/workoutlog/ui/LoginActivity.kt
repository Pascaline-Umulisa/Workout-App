package dev.pascaline.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivityLoginBinding
import dev.pascaline.workoutlog.models.LoginRequest
import dev.pascaline.workoutlog.models.LoginResponse
import dev.pascaline.workoutlog.retrofit.ApiClient
import dev.pascaline.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var sharedPreps:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreps=getSharedPreferences("WORKOUT_PREPS", MODE_PRIVATE)

       binding.btnLogin.setOnClickListener {
          validateLogin()
        }


      binding.tvSighUp.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
          finish()
        }
    }
    fun validateLogin(){
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            binding.tilEmail.error=getString(R.string.Email_required)
             error=true
        }
        if (password.isBlank()){
           binding.tilPassword.error=getString(R.string.Password_required)
             error=true
        }
        if (!error){
            binding.pbLogin.visibility=View.VISIBLE
            var loginRequest=LoginRequest(email,password)
            makeLoginRequest(loginRequest)
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
        }
    }

    fun makeLoginRequest(loginRequest:LoginRequest){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.login(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin.visibility=View.GONE
                if(response.isSuccessful){
                    var loginResponse=response.body()
                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                    finish()
                }
                else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility=View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun saveLoginDetails(loginResponse:LoginResponse){

    }
}