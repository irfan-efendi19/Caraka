package com.bangkit.caraka.ui.detailhistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityDetailHistoryBinding
import com.bumptech.glide.Glide

class DetailHistoryActivity : AppCompatActivity() {

    private var _binding: ActivityDetailHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleHistory.text = intent.getStringExtra(JUDULARTIKEL)
        binding.descHistory.text = intent.getStringExtra(ARTIKEL)

        val imageUrl = intent.getStringExtra(THUMNAIL)
        if (!imageUrl.isNullOrBlank()) {
            val imageView = findViewById<ImageView>(R.id.img_item_photo_history)
            Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .into(imageView)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sejarah"
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

    companion object {
        const val JUDULARTIKEL = "judul_aksara"
        const val THUMNAIL = "thumnail"
        const val ARTIKEL = "artikel"
    }
}