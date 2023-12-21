package com.bangkit.caraka.ui.camera

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.bangkit.caraka.databinding.ActivityScannerResultBinding
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.utill.getRotatedBitmap
import com.bangkit.caraka.utill.showToast
import java.io.File

class ScannerResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerResultBinding
    private lateinit var scannerViewModel: ScannerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        scannerViewModel = ViewModelProvider(this, viewModelFactory)[ScannerViewModel::class.java]

        val imageUrlCamera = intent.getStringExtra(ScannerActivity.EXTRA_CAMERA_IMAGE)?.toUri()
        val imageUrlGallery = intent.getStringExtra(ScannerActivity.EXTRA_GALLERY_IMAGE)?.toUri()
        val scanResult = intent.getStringExtra(ScannerActivity.EXTRA_RESULT_SCANNER)

        if (imageUrlCamera != null){
            val bitmap = BitmapFactory.decodeFile(imageUrlCamera.path)
            val rotatedBitmap = bitmap.getRotatedBitmap(File(imageUrlCamera.path.toString()))
            binding.ivPhotoResultScan.setImageBitmap(rotatedBitmap)
            binding.ivPhotoResultScan.scaleType = ImageView.ScaleType.CENTER_CROP
        } else if (imageUrlGallery != null){
            binding.ivPhotoResultScan.setImageURI(imageUrlGallery)
            binding.ivPhotoResultScan.scaleType = ImageView.ScaleType.CENTER_CROP
        }else{
            showToast(this, "Image Null")
        }

        binding.btnDeteksiUlang.setOnClickListener{
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }

        if (scanResult != null){
            binding.tvScanResult.text = scanResult.toString()
        } else {
            showToast(this, "result null")
        }


//        scannerViewModel.scanImageResult()
//        scannerViewModel.scanResponse.observe(this){ response ->
//            binding.tvScanResult.text = response.result
//        }
    }
}

