package dev.pascaline.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.pascaline.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

   lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        castViews()
        setupBottomNav()

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