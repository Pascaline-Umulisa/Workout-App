package dev.pascaline.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var tvSignUp:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        tvSignUp=findViewById(R.id.tvSighUp)
        btnLogin.setOnClickListener {
          validateLogin()


        }
        tvSignUp.setOnClickListener {
            val intent= Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateLogin(){
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        var error=false
        if (email.isBlank()){
            tilEmail.error=getString(R.string.Email_required)
             error=true
        }
        if (password.isBlank()){
            tilPassword.error=getString(R.string.Password_required)
             error=true
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }
}