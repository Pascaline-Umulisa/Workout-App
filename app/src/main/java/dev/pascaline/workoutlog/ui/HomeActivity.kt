package dev.pascaline.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

   lateinit var binding:ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        castViews()
        setupBottomNav()


        binding.tvLog.setOnClickListener {
            sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", Context.MODE_PRIVATE)
            val editor=sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN", "")
            editor.putString("USER_ID", "")
            editor.putString("PROFILE_ID", "")
            editor.apply()
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

//    fun castViews() {
//        fcvHome = findViewById(R.id.fcvHome)
//        bnvHome = findViewById(R.id.bnvHome)
//    }

    fun setupBottomNav() {
       binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    val transaction = supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.track -> {
                    val transaction = supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile -> {

                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true

                }
                R.id.home -> {

                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, HomeFragment()).commit()
                    true

                }
                else -> false


            }
        }
    }
}



//when (item.itemId) {
//    R.id.plan -> {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fcvHome, PlanFragment())
//        transaction.commit()
//        true
//    }
//}