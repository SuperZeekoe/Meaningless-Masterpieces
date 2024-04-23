//package com.example.myapplication.ui.dashboard
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.view.Display
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.lifecycleScope
//import com.example.myapplication.R
//import com.example.myapplication.databinding.FragmentDashboardBinding
//import java.io.File
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//import android.view.Surface.ROTATION_0
//import android.widget.Button
//import android.widget.ImageView
//
//
//
//class DashboardFragment : Fragment() {
//
//    private var _binding: FragmentDashboardBinding? = null
//    private val binding get() = _binding!!
//
//    private val cameraPermissionCode = 101
//    private lateinit var cameraExecutor: ExecutorService
//    private var imageCapture: ImageCapture? = null
//
//    private lateinit var outputDirectory: File
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//
//    private lateinit var capturedImageView: ImageView
//    private lateinit var deleteButton: Button
//    private lateinit var acceptButton: Button
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        cameraExecutor = Executors.newSingleThreadExecutor()
//        outputDirectory = getOutputDirectory()
//
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.CAMERA
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(Manifest.permission.CAMERA),
//                cameraPermissionCode
//            )
//        } else {
//            startCamera()
//        }
//
//        capturedImageView = view.findViewById(R.id.capturedImageView)
//        deleteButton = view.findViewById(R.id.button_delete)
//        acceptButton = view.findViewById(R.id.button_accept)
//
//        binding.buttonTakePicture.setOnClickListener {
//            takePhoto()
//        }
//        deleteButton.setOnClickListener{
//            deletePhoto()
//        }
//        acceptButton.setOnClickListener{
//            acceptPhoto()
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//        cameraExecutor.shutdown()
//    }
//
//
//    private fun startCamera() {
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
//        cameraProviderFuture.addListener({
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//            val rotation = binding.previewView.display.rotation
//            val preview = Preview.Builder()
//                .build()
//                .also {
//                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
//                }
//
//
//            imageCapture = ImageCapture.Builder()
//                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
//                .build()
//
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//
//                cameraProvider.bindToLifecycle(
//                    this, cameraSelector, preview, imageCapture
//                )
//            } catch (exc: Exception) {
//
//            }
//
//        }, ContextCompat.getMainExecutor(requireContext()))
//    }
//
//
////    private fun getRotation(): Int {
////        return Surface.ROTATION_90
////    }
//
//
//    private fun takePhoto() {
//        val imageCapture = imageCapture ?: return
//
//
//
//        val photoFile = File(
//            outputDirectory,
//            "${System.currentTimeMillis()}.jpg"
//        )
//
//        imageCapture.targetRotation = ROTATION_0
//        imageCapture.targetRotation
//
//
//
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//
//        imageCapture.takePicture(
//            outputOptions,
//            ContextCompat.getMainExecutor(requireContext()),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onError(exc: ImageCaptureException) {
//                    Log.e("CameraX", "Photo capture failed: ${exc.message}", exc)
//
//                }
//                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                    val savedUri = Uri.fromFile(photoFile)
//                    val msg = "Photo capture succeeded: $savedUri"
//                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
//                    Log.d("imageSuccess", "picture work")
//                    showCapturedImage(photoFile)
//                }
//            }
//        )
//    }
//
//    private fun showCapturedImage(photoFile: File) {
//        val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
//        capturedImageView.setImageBitmap(bitmap)
//
//        deleteButton.visibility = View.VISIBLE
//        acceptButton.visibility = View.VISIBLE
//    }
//
//    private fun deletePhoto() {
//        capturedImageView.setImageBitmap(null)
//        deleteButton.visibility = View.GONE
//        acceptButton.visibility = View.GONE
//    }
//    private fun acceptPhoto() {
//        capturedImageView.setImageBitmap(null)
//        deleteButton.visibility = View.GONE
//        acceptButton.visibility = View.GONE
//    }
//
//    private fun getOutputDirectory(): File {
//        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
//            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
//        }
//        val outputDir = if (mediaDir != null && mediaDir.exists()) mediaDir else requireActivity().filesDir
//        Log.d(TAG, "Output directory: ${outputDir.absolutePath}")
//        return outputDir
//    }
//
//    companion object {
//        private const val TAG = "DashboardFragment"
//        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
//    }
//}
