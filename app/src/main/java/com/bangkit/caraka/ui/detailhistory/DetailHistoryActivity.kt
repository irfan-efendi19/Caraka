package com.bangkit.caraka.ui.detailhistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {

    private var _binding: ActivityDetailHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleHistory.text = intent.getStringExtra(JUDULARTIKEL)
        binding.descHistory.text = intent.getStringExtra(ARTIKEL)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val JUDULARTIKEL = "judul_aksara"
        const val THUMNAIL = "thumnail"
        const val ARTIKEL = "artikel"
    }
}