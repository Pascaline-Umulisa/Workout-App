package dev.pascaline.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.pascaline.workoutlog.databinding.ActivityTeasingBinding

class TeasingActivity : AppCompatActivity() {
    lateinit var binding: ActivityTeasingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTeasingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}