package dev.pascaline.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivitySignUpBinding
import dev.pascaline.workoutlog.models.RegisterRequest
import dev.pascaline.workoutlog.models.RegisterResponse
import dev.pascaline.workoutlog.api.ApiClient
import dev.pascaline.workoutlog.api.ApiInterface
import dev.pascaline.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
   lateinit var binding:ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignUp.setOnClickListener {
            verifyInputs()
        }

        binding.tvLogin.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer { registerResponse->
//            var message=response.body()?.message
            Toast.makeText(baseContext,registerResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,LoginActivity::class.java))
        })

        userViewModel.registerErrorLiveData.observe(this, Observer { error->
//            var error=response.errorBody()?.string()
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }

    fun verifyInputs(){
        var first=binding.etFirstName.text.toString()
        var last=binding.etLastName.text.toString()
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var confirm=binding.etConfirm.text.toString()
        var phoneNumber=binding.etPhone.text.toString()
        var error=false
        if (first.isBlank()){
            error=true
            binding.tilFirstName.error=getString(R.string.FirstName_required)
        }
        if (last.isBlank()){
            error=true
            binding.tilLastName.error=getString(R.string.LastName_required)
        }
        if (email.isBlank()){
            error=true
            binding.tilEmail.error=getString(R.string.Email_required)
        }
        if (phoneNumber.isBlank()){
            error=true
            binding.tilPhone.error="Phone number is required"
        }
        if (password.isBlank()){
            error=true
            binding.tilPassword.error=getString(R.string.Password_required)
        }
        if (confirm.isBlank()){
            error=true
            binding.tilConfirm.error=getString(R.string.confirm_password)
        }
        if(password!=confirm){
            error=true
            binding.tilConfirm.error="Passwords must match"
        }
        if(!error){
            val registerRequest=RegisterRequest(first,last,email,phoneNumber,password)
            userViewModel.registerUser(registerRequest)
        }
    }

}