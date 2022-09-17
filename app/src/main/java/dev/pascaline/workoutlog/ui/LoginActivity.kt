package dev.pascaline.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivityLoginBinding
import dev.pascaline.workoutlog.models.LoginRequest
import dev.pascaline.workoutlog.models.LoginResponse
import dev.pascaline.workoutlog.api.ApiClient
import dev.pascaline.workoutlog.api.ApiInterface
import dev.pascaline.workoutlog.util.Constants
import dev.pascaline.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var sharedPreps:SharedPreferences
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreps=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

       binding.btnLogin.setOnClickListener {
          validateLogin()
        }


      binding.tvSighUp.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
          finish()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErroLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
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
            userViewModel.loginUser(loginRequest)
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
        }
    }

    fun saveLoginDetails(loginResponse:LoginResponse){
        val editor=sharedPreps.edit()
        val token="Bearer ${loginResponse.accessToken}"
        editor.putString(Constants.accessToken,token)
        editor.putString(Constants.userId, loginResponse.userId)
        editor.putString(Constants.profileId, loginResponse.profileId)
        editor.apply()
    }

}