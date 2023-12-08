package com.bangkit.caraka.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityHomeBinding
import com.bangkit.caraka.databinding.FragmentStartQuestionBinding


class StartQuestionFragment : Fragment() {

    private lateinit var binding: FragmentStartQuestionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_start_question,container,false)

        binding.startBtn.setOnClickListener {
            val action = StartQuestionFragmentDirections.actionStartFragmentToQuizFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}