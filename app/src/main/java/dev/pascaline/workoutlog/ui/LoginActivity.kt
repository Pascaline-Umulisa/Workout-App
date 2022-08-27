package dev.pascaline.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}