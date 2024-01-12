package com.bangkit.caraka.ui.camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.Surface
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkit.caraka.R
import com.bangkit.caraka.data.networking.response.UploadResponse
import com.bangkit.caraka.databinding.ActivityScannerBinding
import com.bangkit.caraka.ui.ViewModelFactory
import com.bangkit.caraka.utill.createCustomTempFile
import com.bangkit.caraka.utill.isNetworkConnected
import com.bangkit.caraka.utill.reduceFileImage
import com.bangkit.caraka.utill.showToast
import com.bangkit.caraka.utill.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception

class ScannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScannerBinding
    private lateinit var scannerViewModel: ScannerViewModel

    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    var imageCapture: ImageCapture? = null
    var currentImageUri: Uri? = null
    var resultText: String? = null
    var isCameraImage: Boolean = false
    private var isGalleryImage: Boolean = false
    private lateinit var uploadResponseObserver: Observer<UploadResponse>
    private var daerah: String? = null
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        scannerViewModel = ViewModelProvider(this, viewModelFactory)[ScannerViewModel::class.java]

        context = this@ScannerActivity
        daerah = intent.getStringExtra(CameraActivity.EXTRA_DAERAH).toString()

        binding.constraintLayoutScannerHolder.visibility = View.VISIBLE
        binding.switchCamera.setOnClickListener {
            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
            startCamera()
        }

        binding.captureImage.setOnClickListener {
            if (isNetworkConnected(context)) {
                takePhoto()
            } else {
                showToast(this, "No internet connection.")
            }
        }

        binding.btnGallery.setOnClickListener {
            if (isNetworkConnected(context)) {
                startGallery()
            } else {
                showToast(this, "No internet connection.")
            }
        }

        showToast(this, "Menggunakan Scanner $daerah")

        uploadResponseObserver = Observer { response ->
            val succes = response.status == null
            val failed = response.status == "fail"
            Log.d("Scanner Activity", "Perform Observe")
            when {
                succes -> {
                    resultText = response.hasil
                    binding.progressBarScanner.visibility = View.GONE
                    binding.constraintLayoutScannerHolder.visibility = View.VISIBLE
                    val intent = Intent(this@ScannerActivity, ScannerResultActivity::class.java)
                    when {
                        isCameraImage -> {
                            intent.putExtra(EXTRA_CAMERA_IMAGE, currentImageUri.toString())
                            isCameraImage = false
                            response.status = "done"
                        }

                        isGalleryImage -> {
                            intent.putExtra(EXTRA_GALLERY_IMAGE, currentImageUri.toString())
                            isGalleryImage = false
                            response.status = "done"
                        }
                    }
                    intent.putExtra(EXTRA_RESULT_SCANNER, resultText)
                    intent.putExtra(CameraActivity.EXTRA_DAERAH, daerah)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }

                failed -> {
                    isCameraImage = false
                    isGalleryImage = false
                    currentImageUri = null
                    resultText = null
                    response.status = "retry"

                    showToast(this, response.message)
                    Log.d("", response.message.toString())

                    binding.progressBarScanner.visibility = View.GONE
                    binding.constraintLayoutScannerHolder.visibility = View.VISIBLE

                    // Menghentikan observasi setelah menerima respons "fail"
                    scannerViewModel.uploadResponse.removeObserver(uploadResponseObserver)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    override fun onStart() {
        super.onStart()
        orientationEventListener.enable()
    }

    override fun onStop() {
        super.onStop()
        orientationEventListener.disable()
    }

    private val orientationEventListener by lazy {
        object : OrientationEventListener(this) {
            override fun onOrientationChanged(orientation: Int) {
                if (orientation == ORIENTATION_UNKNOWN) {
                    return
                }

                val rotation = when (orientation) {
                    in 45 until 135 -> Surface.ROTATION_270
                    in 135 until 225 -> Surface.ROTATION_180
                    in 225 until 315 -> Surface.ROTATION_90
                    else -> Surface.ROTATION_0
                }
                imageCapture?.targetRotation = rotation
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                showToast(this, getString(R.string.fail_to_show_camera))
                Log.d(TAG, "startCamera: ${exc.message}")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = createCustomTempFile(application)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {

                    currentImageUri = output.savedUri
                    val imageFile = uriToFile(currentImageUri!!, this@ScannerActivity)
                    isCameraImage = true
                    uploadImage(imageFile)
                    binding.progressBarScanner.visibility = View.VISIBLE
                    binding.constraintLayoutScannerHolder.visibility = View.GONE

//                    scannerViewModel.uploadResponse.observe(this@ScannerActivity){ response ->
//                        if(!response.error){
//                            val intent = Intent(this@ScannerActivity, ScannerResultActivity::class.java)
//                            intent.putExtra(EXTRA_IMAGE, output.savedUri.toString())
//                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                            startActivity(intent)
//                            finish()
//                        } else {
//                            showToast(this@ScannerActivity, response.message)
//                        }
//                    }
                }

                override fun onError(exc: ImageCaptureException) {
                    showToast(this@ScannerActivity, getString(R.string.fail_to_take_picture))
                    Log.e(TAG, "onError: ${exc.message}")
                }
            }
        )
    }


    private fun startGallery() {
        launchGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launchGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            val imageFile = uriToFile(currentImageUri!!, this@ScannerActivity)
            isGalleryImage = true
            uploadImage(imageFile)
            binding.progressBarScanner.visibility = View.VISIBLE
            binding.constraintLayoutScannerHolder.visibility = View.GONE

//                scannerViewModel.uploadResponse.observe(this){response ->
//                    if (!response.error) {
//                        val intent = Intent(this@ScannerActivity, ScannerResultActivity::class.java)
//                        intent.putExtra(EXTRA_GALLERY_IMAGE, imageFile.toString())
//                        startActivity(intent)
//                    } else {
//                        showToast(this@ScannerActivity, "Error")
//                    }
//                }
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }


    private fun uploadImage(image: File) {
        currentImageUri?.let { uri ->
            uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${image.path}")
            val requestImageFile = image.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "file",
                image.name,
                requestImageFile
            )
            if (daerah != null) {
                scannerViewModel.uploadFile(multipartBody, daerah!!)
            } else {
                Log.d("Scanner Activity", "Daerah : $daerah")
            }
            restartObservation()
            Log.i("uploadImage", "file diupload")
        }
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }


    private fun restartObservation() {
        // Memulai observasi lagi setelah menghentikannya
        scannerViewModel.uploadResponse.observe(this, uploadResponseObserver)
    }

    companion object {
        private const val TAG = "CameraActivity"
        const val EXTRA_CAMERA_IMAGE = "CameraImage"
        const val EXTRA_GALLERY_IMAGE = "GalleryImage"
        const val EXTRA_RESULT_SCANNER = "ResultScanner"
    }
}