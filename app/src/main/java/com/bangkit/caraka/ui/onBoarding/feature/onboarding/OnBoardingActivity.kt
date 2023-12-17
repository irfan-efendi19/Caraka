package com.bangkit.caraka.ui.onBoarding.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.caraka.databinding.OnboardingActivityBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: OnboardingActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OnboardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}
