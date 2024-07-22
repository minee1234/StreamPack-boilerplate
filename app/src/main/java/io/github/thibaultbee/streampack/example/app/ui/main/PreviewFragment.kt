package io.github.thibaultbee.streampack.example.app.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.thibaultbee.streampack.example.app.configuration.Configuration
import io.github.thibaultbee.streampack.example.app.utils.DialogUtils
import io.github.thibaultbee.streampack.example.app.utils.PermissionManager
import io.github.thibaultbee.streampack.example.app.utils.StreamerManager
import io.github.thibaultbee.streampack.example.databinding.MainFragmentBinding
import io.github.thibaultbee.streampack.views.PreviewView

class PreviewFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    private val viewModel: PreviewViewModel by lazy {
        ViewModelProvider(
            this,
            PreviewViewModelFactory(
                StreamerManager(
                    requireContext(),
                    Configuration(requireContext())
                )
            )
        )[PreviewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        bindProperties()
        return binding.root
    }

    @SuppressLint("MissingPermission")
    private fun bindProperties() {
        binding.liveButton.setOnClickListener {
            requestStreamerPermissions(viewModel.requiredPermissions)
        }

        viewModel.streamerError.observe(viewLifecycleOwner) {
            showError("Oops", it)
        }
    }

    private fun startStopLive() {
        if (binding.liveButton.isChecked) {
            startStream()
        } else {
            stopStream()
        }
    }

    private fun requestStreamerPermissions(permissions: List<String>) {
        when {
            PermissionManager.hasPermissions(
                requireContext(),
                *permissions.toTypedArray()
            ) -> {
                startStopLive()
            }
            else -> {
                requestStreamerPermissionsLauncher.launch(
                    permissions.toTypedArray()
                )
            }
        }
    }

    private fun startStream() {
        /**
         * Lock orientation while stream is running to avoid stream interruption if
         * user turns the device.
         * For landscape only mode, set [requireActivity().requestedOrientation] to
         * [ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE] in [onCreate] or [onResume].
         */
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        viewModel.startStream()
    }

    private fun unLockScreen() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    private fun stopStream() {
        viewModel.stopStream()
        unLockScreen()
    }

    private fun showPermissionError() {
        binding.liveButton.isChecked = false
        unLockScreen()
        DialogUtils.showPermissionAlertDialog(requireContext())
    }

    private fun showPermissionErrorAndFinish() {
        binding.liveButton.isChecked = false
        DialogUtils.showPermissionAlertDialog(requireContext()) { requireActivity().finish() }
    }

    private fun showError(title: String, message: String) {
        binding.liveButton.isChecked = false
        unLockScreen()
        DialogUtils.showAlertDialog(requireContext(), "Error: $title", message)
    }

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        requestCameraAndMicrophonePermissions()
    }

    @SuppressLint("MissingPermission")
    private fun requestCameraAndMicrophonePermissions() {
        when {
            PermissionManager.hasPermissions(
                requireContext(),
                Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
            ) -> {
                createStreamer()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) -> {
                showPermissionError()
                requestCameraAndMicrophonePermissionsLauncher.launch(
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO
                    )
                )
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showPermissionError()
                requestCameraAndMicrophonePermissionsLauncher.launch(
                    arrayOf(
                        Manifest.permission.CAMERA
                    )
                )
            }
            else -> {
                requestCameraAndMicrophonePermissionsLauncher.launch(
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )
            }
        }
    }

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    private fun createStreamer() {
        viewModel.createStreamer()

        // Set camera settings button when camera is started
        binding.preview.listener = object : PreviewView.Listener {
            override fun onPreviewStarted() {
                viewModel.onPreviewStarted()
            }

            override fun onZoomRationOnPinchChanged(zoomRatio: Float) {
                viewModel.onZoomRationOnPinchChanged()
            }
        }

        // Wait till streamer exists to set it to the SurfaceView.
        viewModel.inflateStreamerView(binding.preview)

        // Wait till streamer exists
        lifecycle.addObserver(viewModel.streamerLifeCycleObserver)
    }

    @SuppressLint("MissingPermission")
    private val requestCameraAndMicrophonePermissionsLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.toList().all {
                    it.second
                }) {
                createStreamer()
            } else {
                showPermissionErrorAndFinish()
            }
        }

    private val requestStreamerPermissionsLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.toList().all {
                    it.second
                }) {
                startStopLive()
            } else {
                showPermissionError()
            }
        }
}
