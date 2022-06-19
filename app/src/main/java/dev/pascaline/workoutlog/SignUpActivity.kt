package dev.pascaline.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import dev.pascaline.workoutlog.databinding.ActivitySignUpBinding

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
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun verifyInputs(){
        var first=binding.etFirstName.text.toString()
        var last=binding.etLastName.text.toString()
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var confirm=binding.etConfirm.text.toString()
        if (first.isBlank()){
            binding.tilFirstName.error=getString(R.string.FirstName_required)
        }
        if (last.isBlank()){
            binding.tilLastName.error=getString(R.string.LastName_required)
        }
        if (email.isBlank()){
           binding.tilEmail.error=getString(R.string.Email_required)
        }
        if (password.isBlank()){
            binding.tilPassword.error=getString(R.string.Password_required)
        }
        if (confirm.isBlank()){
           binding.tilConfirm.error=getString(R.string.confirm_password)
        }
        if(password!=confirm){
            binding.tilConfirm.error="Passwords must match"
        }

    }
}