package dev.pascaline.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.pascaline.workoutlog.databinding.FragmentProfileBinding
import dev.pascaline.workoutlog.util.Constants

class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logOut()
    }

    fun logOut(){

        binding.tvLog.setOnClickListener {
            sharedPrefs = this.requireActivity().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
            val editor=sharedPrefs.edit()
            editor.putString(Constants.accessToken, Constants.EMPTY_STRING)
            editor.putString(Constants.userId, Constants.EMPTY_STRING)
            editor.putString(Constants.profileId, Constants.EMPTY_STRING)
            editor.apply()
            startActivity(Intent(this.context,LoginActivity::class.java))

        }
    }
}