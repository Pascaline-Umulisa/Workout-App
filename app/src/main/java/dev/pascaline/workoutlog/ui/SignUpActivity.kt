package dev.pascaline.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivitySignUpBinding
import dev.pascaline.workoutlog.models.RegisterRequest
import dev.pascaline.workoutlog.models.RegisterResponse
import dev.pascaline.workoutlog.retrofit.ApiClient
import dev.pascaline.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
   lateinit var binding:ActivitySignUpBinding

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
            makeRegistrationRequest(registerRequest)
        }
    }
    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=apiClient.registerUser(registerRequest)
        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful){
                    var message=response.body()?.message
                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                }
                else{
                    var error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}