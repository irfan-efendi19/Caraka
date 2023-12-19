package com.bangkit.caraka.ui.bottom_menu.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.caraka.R
import com.bangkit.caraka.adapter.HistoryFragmentAdapter
import com.bangkit.caraka.adapter.HistoryHomeAdapter
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.databinding.FragmentHistoryBinding
import com.bangkit.caraka.databinding.FragmentHomeBinding
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.bottom_menu.home.HomeFragViewModel

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private var artikelId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireContext())
        )[HistoryViewModel::class.java]

        historyViewModel.getArtikel(artikelId).observe(viewLifecycleOwner) {
            setupListKamus(it)
        }
    }


    private fun setupListKamus(artikel: List<Artikel>) {
        val layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvFragmentSejarah.layoutManager = layoutManager
        val adapter = HistoryFragmentAdapter(artikel)
        binding.rvFragmentSejarah.adapter = adapter
    }
}
