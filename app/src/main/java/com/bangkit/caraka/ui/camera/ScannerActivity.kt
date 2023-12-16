package com.bangkit.caraka.ui.camera

import android.Manifest
import android.app.AlertDialog
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.bangkit.caraka.R
import com.bangkit.caraka.databinding.ActivityScannerBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.Locale

class ScannerActivity : AppCompatActivity() {

    private var _binding: ActivityScannerBinding? = null
    private val binding get() = _binding!!
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var scannerViewModel: ScannerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    public override fun onResume() {
        super.onResume()
        // Jalankan Kamera
        startCamera()
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
//                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
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
//                ToastUtils.showToast(this@ScannerActivity, "Gagal memunculkan kamera.\nTerdapat kesalahan pada aplikasi")
            }
        }, ContextCompat.getMainExecutor(this))
    }

//    private fun startGallery(){
//        val intent = Intent()
//        intent.action = Intent.ACTION_GET_CONTENT
//        intent.type = "image/*"
//        val chooser = Intent.createChooser(intent, "Choose a Picture")
//        launcherIntentGallery.launch(chooser)
//    }

//    private val launcherIntentGallery = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val selectedImg = result.data?.data as Uri
//            selectedImg.let { uri ->
//                val myFile = uriToFile(uri, this@ScannerActivity)
//                val intent = Intent(this@ScannerActivity, TransliterasiActivity::class.java)
//                // Kirim Gambarnya ke Network
//                scannerViewModel.fetchScannerResponse(myFile)
//                scannerViewModel.liveDataResponseScanner.observe(this@ScannerActivity){ result->
//                    result.onSuccess {
//                        var resultScanner = ""
//                        if(it.error == null){
//                            it.result.forEach { baris->
//                                baris.forEach {
//                                    resultScanner += it
//                                }
//                            }
//                            intent.putExtra(TransliterasiActivity.HASIL, resultScanner)
//                            intent.putExtra(TransliterasiActivity.STATUS, "berhasil")
//                        }else{
//                            val status = "gagal"
//                            resultScanner = "Gagal terdapat kesalahan pada sistem"
//                            intent.putExtra(TransliterasiActivity.STATUS, status)
//                            intent.putExtra(TransliterasiActivity.HASIL, resultScanner)
//                        }
//                    }
//                    result.onFailure {
//                        val status = "gagal"
//                        val resultFail = "Gagal terdapat kesalahan pada sistem"
//                        intent.putExtra(TransliterasiActivity.STATUS, status)
//                        intent.putExtra(TransliterasiActivity.HASIL, resultFail)
//                    }
//                    startActivity(intent)
//                }
//            }
//        }
//    }

    fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createFile(application)

        try {
            val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
            val outputStream: OutputStream = FileOutputStream(myFile)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            // Handle the exception here, such as logging an error message or displaying a toast
            e.printStackTrace()
        }

        return myFile
    }


    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = createFile(application)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
//                    ToastUtils.showToast(this@ScannerActivity, "Gagal mengambil gambar.")
                }
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = output.savedUri
                    val imageFile = savedUri?.let {
                        uriToFile2(savedUri, this@ScannerActivity)
                    }
                    if (imageFile != null) {
//                        performNetworkRequest(imageFile)
                    }
                }
            }
        )
    }

    private fun uriToFile2(uri: Uri, context: Context): File? {
        val file = File(context.cacheDir, "temp_image.jpg")
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

//    fun performNetworkRequest(imageFile: File){
//        val intent = Intent(this@ScannerActivity, TransliterasiActivity::class.java)
//        scannerViewModel.fetchScannerResponse(imageFile)
//        scannerViewModel.liveDataResponseScanner.observe(this@ScannerActivity){ result->
//            result.onSuccess {
//                var resultScanner = ""
//                if(it.error == null){
//                    it.result.forEach { baris->
//                        baris.forEach {
//                            resultScanner += it
//                        }
//                    }
//                    intent.putExtra(TransliterasiActivity.HASIL, resultScanner)
//                    intent.putExtra(TransliterasiActivity.STATUS, "berhasil")
//                }else{
//                    val status = "gagal"
//                    resultScanner = "Gagal terdapat kesalahan pada sistem"
//                    intent.putExtra(TransliterasiActivity.STATUS, status)
//                    intent.putExtra(TransliterasiActivity.HASIL, resultScanner)
//                }
//            }
//            result.onFailure {
//                val status = "gagal"
//                val resultFail = "Gagal terdapat kesalahan pada sistem"
//                intent.putExtra(TransliterasiActivity.STATUS, status)
//                intent.putExtra(TransliterasiActivity.HASIL, resultFail)
//            }
//            startActivity(intent)
//        }
//    }

    fun createFile(application: Application): File {
        val FILENAME_FORMAT = "dd_MMM_yyyy"

        val timeStamp: String = SimpleDateFormat(
            FILENAME_FORMAT,
            Locale.US
        ).format(System.currentTimeMillis())

        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        val outputDirectory = if (
            mediaDir != null && mediaDir.exists()
        ) mediaDir else application.filesDir

        return File(outputDirectory, "$timeStamp.png")
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
//                ToastUtils.showToast(this@ScannerActivity, "Tidak mendapatkan permission.")
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

//    fun showLoading(isLoading: Boolean){
//        binding.cvLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
//        binding.darkBackground.visibility = if(isLoading) View.VISIBLE else View.GONE
//    }

//    private fun showMaxLimitScanDialog(context: Context) {
//        val builder = AlertDialog.Builder(context)
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)
//
//
//        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
//        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
//        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
//        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)
//
//        imgInformation.setImageResource(R.drawable.img_logo_information)
//        textTitle.text = "Pesan Informasi"
//
//        textDesc.text = "Batas scanning anda sudah habis. Lakukan pembelian langganan untuk melakukan scanning."
//        buttonInformation.text = "Mengerti"
//
//        builder.setView(dialogView)
//        val dialog: AlertDialog = builder.create()
//        buttonInformation.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }

//    private fun showScanTipsDialog(context: Context) {
//        val builder = AlertDialog.Builder(context)
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val dialogView: View = inflater.inflate(R.layout.item_dialog_tips, null)
//
//        val buttonMengerti: Button = dialogView.findViewById(R.id.btn_tips_mengerti)
//        val imageTipsBenar: ImageView = dialogView.findViewById(R.id.img_tips_benar)
//        val imageTipsSalah: ImageView = dialogView.findViewById(R.id.img_tips_salah1)
//        val imageTipsSalah2: ImageView = dialogView.findViewById(R.id.img_tips_salah2)
//        val imageTipsSalah3: ImageView = dialogView.findViewById(R.id.img_tips_salah3)
//        val imageTipsSalah4: ImageView = dialogView.findViewById(R.id.img_tips_salah4)
//
//        imageTipsBenar.setImageResource(R.drawable.tipsbenar)
//        imageTipsSalah.setImageResource(R.drawable.tips_salah1)
//        imageTipsSalah2.setImageResource(R.drawable.tips_salah2)
//        imageTipsSalah3.setImageResource(R.drawable.tips_salah3)
//        imageTipsSalah4.setImageResource(R.drawable.tips_salah4)
//
//        builder.setView(dialogView)
//        val dialog: AlertDialog = builder.create()
//        buttonMengerti.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}