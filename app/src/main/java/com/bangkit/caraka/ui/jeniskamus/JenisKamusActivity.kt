package com.bangkit.caraka.ui.jeniskamus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.caraka.databinding.ActivityJenisKamusBinding
import com.bangkit.caraka.ui.kamus.KamusActivity

class JenisKamusActivity : AppCompatActivity() {

    private var _binding: ActivityJenisKamusBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJenisKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kamus"

        binding.btnJeniskamus.setOnClickListener {
            val intent = Intent(this, KamusActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }
}