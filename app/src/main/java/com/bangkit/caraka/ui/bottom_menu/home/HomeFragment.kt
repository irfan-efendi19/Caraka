package com.bangkit.caraka.ui.bottom_menu.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.caraka.adapter.HistoryHomeAdapter
import com.bangkit.caraka.data.networking.response.HistoryResponse
import com.bangkit.caraka.databinding.FragmentHomeBinding
import com.bangkit.caraka.ui.jeniskamus.JenisKamusActivity
import com.bangkit.caraka.ui.kamus.KamusActivity
import com.bangkit.caraka.ui.quiz.QuizActivity

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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.akasarabaliquiz.setOnClickListener {
            val intent = Intent(requireContext(), QuizActivity::class.java)
            startActivity(intent)
        }

        binding.akasarabalikamus.setOnClickListener {
            val intent = Intent(requireContext(), JenisKamusActivity::class.java)
            startActivity(intent)
        }

        binding.akasarasundaquiz.setOnClickListener {
            showToast("Fitur Ini Akan Segera Hadir")
        }

        binding.akasaralampungquiz.setOnClickListener {
            showToast("Fitur Ini Akan Segera Hadir")
        }

        binding.akasarasundakamus.setOnClickListener {
            showToast("Fitur Ini Akan Segera Hadir")
        }

        binding.akasaralampungkamus.setOnClickListener {
            showToast("Fitur Ini Akan Segera Hadir")
        }

        setupUserRv()
    }

    private fun setupUserRv() {
        binding.rvSejarah.apply {
            val rvLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = rvLayoutManager
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}