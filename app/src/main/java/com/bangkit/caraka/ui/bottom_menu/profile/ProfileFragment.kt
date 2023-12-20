package com.bangkit.caraka.ui.bottom_menu.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bangkit.caraka.databinding.FragmentProfileBinding
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.jeniskamus.JenisKamusActivity
import com.bangkit.caraka.ui.onBoarding.feature.onboarding.OnBoardingActivity
import com.bangkit.caraka.ui.signin.SignInActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLangganan.setOnClickListener {
            val intent = Intent(requireContext(), LanggananActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                viewModel.logout()
            }
            startActivity(Intent(this.context, SignInActivity::class.java))
        }
    }

}