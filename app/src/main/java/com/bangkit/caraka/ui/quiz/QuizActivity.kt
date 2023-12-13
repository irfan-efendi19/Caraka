package com.bangkit.caraka.ui.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)

    }
}