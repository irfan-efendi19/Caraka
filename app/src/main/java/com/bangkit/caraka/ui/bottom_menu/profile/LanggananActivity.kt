package com.bangkit.caraka.ui.bottom_menu.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.caraka.R
import com.bangkit.caraka.adapter.KamusAdapter
import com.bangkit.caraka.adapter.LanggananAdapter
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.database.Langganan
import com.bangkit.caraka.databinding.ActivityKamusBinding
import com.bangkit.caraka.databinding.ActivityLanggananBinding
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.kamus.KamusViewModel

class LanggananActivity : AppCompatActivity() {

    private var _binding: ActivityLanggananBinding? = null
    private val binding get() = _binding!!
    private lateinit var langgananViewModel: LanggananViewModel
    private var langgananId = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLanggananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Langganan"


        val viewModelFactory = ViewModelFactory.getInstance(this)
        langgananViewModel = ViewModelProvider(this, viewModelFactory)[LanggananViewModel::class.java]

        langgananViewModel.getLangganan(langgananId).observe(this) {
            setupRvLangganan(it)
        }


    }

    private fun setupRvLangganan(harga: List<Langganan>) {
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvLangganan.layoutManager = layoutManager
        val adapter = LanggananAdapter(harga)
        binding.rvLangganan.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }
}