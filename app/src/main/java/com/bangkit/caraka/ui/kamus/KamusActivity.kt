package com.bangkit.caraka.ui.kamus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.caraka.adapter.KamusAdapter
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.databinding.ActivityKamusBinding
import com.bangkit.caraka.ui.ViewModelFactory

class KamusActivity : AppCompatActivity() {

    private var _binding: ActivityKamusBinding? = null
    private val binding get() = _binding!!
    private lateinit var kamusViewModel: KamusViewModel
    private var kamusBelajarId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kamus"

        val viewModelFactory = ViewModelFactory.getInstance(this)
        kamusViewModel = ViewModelProvider(this, viewModelFactory)[KamusViewModel::class.java]

        kamusViewModel.getKamus(kamusBelajarId).observe(this) {
            setupListKamus(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    private fun setupListKamus(listKamus: List<Kamus>) {
        val layoutManager = GridLayoutManager(this, 5)
        binding.rvKamusdetail.layoutManager = layoutManager
        val adapter = KamusAdapter(listKamus)
        binding.rvKamusdetail.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}