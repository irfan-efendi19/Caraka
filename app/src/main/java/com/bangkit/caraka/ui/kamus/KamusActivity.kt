package com.bangkit.caraka.ui.kamus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.caraka.R
import com.bangkit.caraka.adapter.KamusAdapter
import com.bangkit.caraka.data.Kamus
import com.bangkit.caraka.databinding.ActivityKamusBinding

class KamusActivity : AppCompatActivity() {

    private var _binding: ActivityKamusBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListKamus(listKamus: List<Kamus>) {
        val layoutManager = GridLayoutManager(this, 5)
        binding.rvKamus.layoutManager = layoutManager
        val adapter = KamusAdapter(listKamus)
        binding.rvKamus.adapter = adapter
    }
}