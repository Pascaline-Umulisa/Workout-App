package dev.pascaline.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.pascaline.workoutlog.R
import dev.pascaline.workoutlog.databinding.ActivityHomeBinding
import dev.pascaline.workoutlog.util.Constants
import dev.pascaline.workoutlog.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {

   lateinit var binding:ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        castViews()
        setupBottomNav()
        sharedPrefs=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        val token=sharedPrefs.getString(Constants.accessToken,Constants.EMPTY_STRING)
        exerciseViewModel.fetchExerciseCategories(token!!)


        binding.tvLog.setOnClickListener {
            sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
            val editor=sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN", "")
            editor.putString("USER_ID", "")
            editor.putString("PROFILE_ID", "")
            editor.apply()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
            Toast.makeText(baseContext, "fetched ${exerciseCategories.size} categories", Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg->
            Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show()
        })
    }



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