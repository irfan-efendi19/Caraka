package com.bangkit.caraka.ui.signup

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
import com.bangkit.caraka.R
import com.bangkit.caraka.data.ResultData
import com.bangkit.caraka.data.di.RegisterResponse
import com.bangkit.caraka.databinding.ActivitySignUpBinding
import com.bangkit.caraka.databinding.FragmentHomeBinding
import com.bangkit.caraka.ui.HomeActivity
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.signin.SignInActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignUpViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        showLoading(false)
        supportActionBar?.hide()
        setupView()
        setupAction()

        binding.signinButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
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
        binding.signupButton.setOnClickListener {
            showLoading(true)
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (name.isEmpty()) {
                binding.nameEditTextLayout.error = "Nama Tidak Boleh Kosong"
            } else if (email.isEmpty()) {
                binding.emailEditTextLayout.error = "Email Tidak Boleh Kosong"
            } else if (password.isEmpty()) {
                binding.passwordEditTextLayout.error = "Password Tidak Boleh Kosong"
            }

            lifecycleScope.launch {
                try {
                    viewModel.register(name, email, password)
                        .observe(this@SignUpActivity) { result ->
                            if (result != null) {
                                when (result) {
                                    is ResultData.Loading -> {
                                        showLoading(true)
                                    }

                                    is ResultData.Success -> {
                                        showLoading(false)
                                        showToast("Berhasil Mendaftar Akun")
                                        val intent =
                                            Intent(this@SignUpActivity, SignInActivity::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        startActivity(intent)
                                        finish()
                                    }

                                    is ResultData.Error -> {
                                        showLoading(false)
                                        showToast(result.error)
                                    }
                                }
                            }
                        }
                } catch (e: HttpException) {
                    showLoading(false)
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                    showToast(errorResponse.message)
                }
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarSignup.visibility =
            if (isLoading) View.VISIBLE else View.GONE
        binding.signupButton.isEnabled = !isLoading
    }
}