package com.bangkit.caraka.ui.bottom_menu.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.caraka.adapter.HistoryHomeAdapter
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.databinding.FragmentHomeBinding
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.jeniskamus.JenisKamusActivity
import com.bangkit.caraka.ui.quiz.QuizActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeFragViewModel: HomeFragViewModel
    private var artikelId = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireContext())
        )[HomeFragViewModel::class.java]

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

        homeFragViewModel.getArtikel(artikelId).observe(viewLifecycleOwner) {
            setupListKamus(it)
        }
    }

    private fun setupListKamus(artikel: List<Artikel>) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSejarah.layoutManager = layoutManager
        val adapter = HistoryHomeAdapter(artikel)
        binding.rvSejarah.adapter = adapter
    }

    private fun showToast(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}