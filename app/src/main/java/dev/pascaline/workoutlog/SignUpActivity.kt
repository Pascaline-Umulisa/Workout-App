package dev.pascaline.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var tilFirstName:TextInputLayout
    lateinit var tilLastName:TextInputLayout
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var tilConfirm:TextInputLayout
    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirm: EditText
    lateinit var btnSignUp: Button
    lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        tilFirstName=findViewById(R.id.tilFirstName)
        tilLastName=findViewById(R.id.tilLastName)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        tilConfirm=findViewById(R.id.tilConfirm)
        etFirstName=findViewById(R.id.etFirstName)
        etLastName=findViewById(R.id.etLastName)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        etConfirm=findViewById(R.id.etConfirm)
        btnSignUp=findViewById(R.id.btnSignUp)
        tvLogin=findViewById(R.id.tvLogin)

        btnSignUp.setOnClickListener {
            verifyInputs()
        }

        tvLogin.setOnClickListener {
            val intent= Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun verifyInputs(){
        var first=etFirstName.text.toString()
        var last=etLastName.text.toString()
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        var confirm=etConfirm.text.toString()
        if (first.isBlank()){
            tilFirstName.error=getString(R.string.FirstName_required)
        }
        if (last.isBlank()){
            tilLastName.error=getString(R.string.LastName_required)
        }
        if (email.isBlank()){
            tilEmail.error=getString(R.string.Email_required)
        }
        if (password.isBlank()){
            tilPassword.error=getString(R.string.Password_required)
        }
        if (confirm.isBlank()){
            tilConfirm.error=getString(R.string.confirm_password)
        }

    }
}