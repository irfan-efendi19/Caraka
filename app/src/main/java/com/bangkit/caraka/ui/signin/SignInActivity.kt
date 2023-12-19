package com.bangkit.caraka.ui.signin

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bangkit.caraka.data.networking.repository.ResultData
import com.bangkit.caraka.data.networking.response.LoginResponse
import com.bangkit.caraka.data.networking.userPreference.UserModel
import com.bangkit.caraka.databinding.ActivitySigninBinding
import com.bangkit.caraka.ui.HomeActivity
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.signup.SignUpActivity
import com.bangkit.caraka.utill.showToast
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignInActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignInViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var _binding: ActivitySigninBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        showLoading(false)
        setupAction()
        setupView()

        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.signinButton.setOnClickListener {
            try {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (email.isEmpty()) {
                    binding.emailEditText.error = "Email Tidak Boleh Kosong"
                } else if (password.isEmpty()) {
                    binding.passwordEditText.error = "Password Tidak Boleh Kosong"
                } else {
                    lifecycleScope.launch {
                        viewModel.login(email, password).observe(this@SignInActivity) { result ->
                            if (result != null) {
                                when (result) {
                                    is ResultData.Loading -> {
                                        showLoading(true)
                                    }

                                    is ResultData.Success -> {
                                        showLoading(false)
                                        showToast(this@SignInActivity, "Berhasil Masuk!")
                                        lifecycleScope.launch {
                                            save(
                                                UserModel(
                                                    result.data.loginResult?.token.toString(),
                                                    result.data.loginResult?.name.toString(),
                                                    result.data.loginResult?.userId.toString(),
                                                    true
                                                )
                                            )
                                        }
                                    }

                                    is ResultData.Error -> {
                                        showLoading(false)
                                        showToast(this@SignInActivity, result.error)
                                    }

                                    else -> {

                                    }
                                }
                            }
                        }
                    }
                }

            } catch (e: HttpException) {
                showLoading(false)
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
                showToast(this, errorResponse.message)
            }

        }
    }

    private fun save(session: UserModel) {
        lifecycleScope.launch {
            viewModel.saveSession(session)
            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ViewModelFactory.clearInstance()
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarSignin.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.signinButton.isEnabled = !isLoading
    }
}