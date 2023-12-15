package com.bangkit.caraka.ui.bottom_menu.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.FragmentHomeBinding
import com.bangkit.caraka.ui.quiz.StartQuestionFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.akasarabaliquiz.setOnClickListener {
            val fragmentB = StartQuestionFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.StartQuestionFragment, fragmentB, "fragmentId")?.commit();
//            val action = HomeFragmentDirections.actionHomeFragmentToStartQuestionFragment()
//            findNavController().navigate(action)
        }

        binding.akasarasundaquiz.setOnClickListener {
            showToast("Fitur Ini Akan Segera Hadir")
        }

        binding.akasaralampungquiz.setOnClickListener {
            showToast("Fitur Ini Akan Segera Hadir")
        }

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root

    }

    private fun showToast(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}