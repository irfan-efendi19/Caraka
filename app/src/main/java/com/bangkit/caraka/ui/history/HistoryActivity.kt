package com.bangkit.caraka.ui.history

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.caraka.adapter.HistoryAdapter
import com.bangkit.caraka.databinding.ActivityHistoryBinding
import com.bangkit.caraka.data.networking.service.ApiConfig
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.ui.signin.SignInActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val historyViewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        binding.root.visibility = View.INVISIBLE

        setUpViewModel()
    }

    private fun setUpViewModel(){
//        historyViewModel.getSession().observe(this){ user ->
//            Log.d(TAG, "user: ${user.name}")
//            Log.d(TAG, "isLogin: ${user.isLogin}")
//            Log.d(TAG, "Token: ${user.token}")
//
//            if (user.isLogin) {
//                binding.root.visibility = View.VISIBLE
//                ApiConfig.token = user.token
//                Log.d(TAG, "try to fetch data")
//                historyViewModel.fetchHistory()
//                historyViewModel.historyLiveData.observe(this){
//                    val adapter = HistoryAdapter(it.listStory)
//                    binding.rvSejarahActivity.layoutManager = LinearLayoutManager(this)
//                    binding.rvSejarahActivity.adapter = adapter
//                }
//
//            } else {
//                Log.d(TAG, "Tidak Ada User")
//                startActivity(Intent(this, SignInActivity::class.java))
//                finish()
//            }
//        }
    }
}