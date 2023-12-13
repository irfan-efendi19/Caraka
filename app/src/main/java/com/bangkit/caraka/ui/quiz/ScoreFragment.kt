package com.bangkit.caraka.ui.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.FragmentHomeBinding
import com.bangkit.caraka.databinding.FragmentScoreBinding
import com.bangkit.caraka.ui.HomeActivity
import com.bangkit.caraka.ui.camera.CameraActivity


class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private var _binding: FragmentScoreBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        val scoreArgs by navArgs<ScoreFragmentArgs>()
        binding.scoreNumber.text = scoreArgs.score.toString()

        binding.tryagain.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_scoreFragment_to_startFragment)
        }

        binding.returnmenu.setOnClickListener {
            val intent = Intent(this.context, HomeActivity::class.java)
            startActivity(intent)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            view?.findNavController()?.navigate(R.id.action_scoreFragment_to_startFragment)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}