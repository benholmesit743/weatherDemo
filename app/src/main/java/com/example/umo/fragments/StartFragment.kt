package com.example.umo.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.umo.R
import com.example.umo.data.ZipCode
import com.example.umo.databinding.FragmentStartBinding
import com.example.umo.recyclerView.StartAdapter
import com.example.umo.viewModels.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.zip_dialog.*
import kotlinx.android.synthetic.main.zip_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.*


class StartFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel by activityViewModel<MainViewModel>()
    private lateinit var binding: FragmentStartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocationPermission()
        val onClick: (data: ZipCode) -> Unit = {
            viewModel.currentItem = it
            Navigation.findNavController(binding.root).navigate(R.id.action_start_to_forecast)
            viewModel.getCurrentTemperatureFromApi()
        }
        Glide.with(requireContext())
            .load(requireContext().resources.getString(R.string.app_bar_image_url))
            .into(binding.image)

        add_button.setOnClickListener {
            showEditTextDialog()
        }

        viewModel.startData.observe(viewLifecycleOwner) {
            if (it != null) {
                if (binding.recyclerView.adapter == null) {
                    binding.recyclerView.adapter = StartAdapter(ArrayList(it), onClick)
                } else {
                    (binding.recyclerView.adapter as StartAdapter).updateList(it)
                }
                if (!binding.recyclerView.hasFixedSize()) binding.recyclerView.setHasFixedSize(true)
            }
        }
    }

    private fun showEditTextDialog() {
        val dialog = AlertDialog.Builder(requireContext()).apply {
            val zipDialog = LayoutInflater.from(requireContext()).inflate(R.layout.zip_dialog, null)
            setView(zipDialog)
            setTitle(requireContext().getString(R.string.zip_dialog_title))
            setPositiveButton(android.R.string.ok
            ) { dialog, _ ->
                viewModel.addToRepo(
                    ZipCode(
                        unit = 0,
                        zipCode = zipDialog.editText.text.toString(),
                        temperature = null,
                        timeStamp = null
                    )
                )
                dialog.dismiss()
            }
            setNegativeButton(android.R.string.cancel
            ) { dialog, _ -> dialog.cancel() }
        }.create()
        dialog.show()
    }

    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    private fun requestLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                    // Precise location access granted.
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location? ->
                            // Got last known location. In some rare situations this can be null.
                            location?.let {
                                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                                val postalCode = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                                    ?.first()?.postalCode
                                postalCode?.let { code ->
                                    viewModel.addToRepo(
                                        ZipCode(
                                            zipCode = code,
                                            temperature = null,
                                            timeStamp = null,
                                            unit = 0
                                        )
                                    )
                                }
                            }
                        }
                }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        ))
    }
}