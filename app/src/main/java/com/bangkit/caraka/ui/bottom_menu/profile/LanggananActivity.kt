package com.bangkit.caraka.ui.bottom_menu.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityKamusBinding
import com.bangkit.caraka.databinding.ActivityLanggananBinding

class LanggananActivity : AppCompatActivity() {

    private var _binding: ActivityLanggananBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLanggananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Langganan"

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }
}