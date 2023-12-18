package com.bangkit.caraka.ui.levelquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.caraka.R

class LevelQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_quiz)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }
}