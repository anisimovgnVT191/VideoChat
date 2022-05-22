package com.example.android.videochat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.android.videochat.databinding.ActivityMainBinding
import com.example.android.videochat.presentation.callfeature.CallFragment
import com.example.android.videochat.presentation.models.UserType
import com.example.android.videochat.presentation.startcallfeature.StartCallFragment


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateToStartCallFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navigateToStartCallFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, StartCallFragment())
            .commit()
    }

    fun navigateToCallFragment(callId: String, userType: UserType) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, CallFragment.newInstance(callId, userType))
            .commit()
    }
}