package com.bangkit.caraka.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityHomeBinding
import com.bangkit.caraka.ui.camera.CameraActivity
import com.bangkit.caraka.ui.onBoarding.feature.onboarding.OnBoardingActivity
import com.bangkit.caraka.ui.onBoarding.prefmanager.OnBoardingPrefManager
import com.bangkit.caraka.ui.signin.SignInActivity
import com.bangkit.caraka.ui.signup.SignUpActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityHomeBinding
    private lateinit var onBoardingPrefManager: OnBoardingPrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        onBoardingPrefManager = OnBoardingPrefManager(this)

        if (checkIsFirstTimeLaunch()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        } else {
            binding.container.visibility = View.VISIBLE
            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment_activity_home)
            AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_history,
                R.id.navigation_terjemahan,
                R.id.navigation_profile
            ).build()


            navView.setupWithNavController(navController)
        }

        binding.cameraButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        getSession()
    }

    //fungsi mencek apakah aplikasi pertama kali diluncurkan atau tidak
    private fun checkIsFirstTimeLaunch(): Boolean {
        return onBoardingPrefManager.isFirstTimeLaunch
    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                lifecycleScope.launch {
//                    viewModel.getStory.observe(this@HomeActivity) { result ->
//                        adapter.submitData(lifecycle, result)
//                        showLoading(false)
//                    }
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBarMain.visibility = View.VISIBLE
        else binding.progressBarMain.visibility = View.GONE
    }

}