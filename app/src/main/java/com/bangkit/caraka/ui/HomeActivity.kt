package com.bangkit.caraka.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityHomeBinding
import com.bangkit.caraka.ui.camera.CameraActivity
import com.bangkit.caraka.ui.onboarding.prefmanager.OnBoardingPrefManager
import com.bangkit.caraka.ui.onBoarding.feature.onboarding.OnBoardingActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var onBoardingPrefManager: OnBoardingPrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBoardingPrefManager = OnBoardingPrefManager(this)

        if (checkIsFirstTimeLaunch()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        } else {
            binding.container.visibility = View.VISIBLE
            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment_activity_home)
            AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile
            ).build()


            navView.setupWithNavController(navController)
        }

        binding.cameraButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }

    //fungsi mencek apakah aplikasi pertama kali diluncurkan atau tidak
    private fun checkIsFirstTimeLaunch(): Boolean {
        return onBoardingPrefManager.isFirstTimeLaunch
    }

}