package com.example.umo.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.umo.R
import com.example.umo.data.CapitalData
import com.example.umo.databinding.FragmentStartBinding
import com.example.umo.recyclerView.StartAdapter
import com.example.umo.viewModels.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.*
import kotlin.collections.ArrayList


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
        val onClick: (data: CapitalData) -> Unit = {
            viewModel.currentItem = it
            Navigation.findNavController(binding.root).navigate(R.id.action_start_to_forecast)
            viewModel.getFiveDayForecastFromApi()
        }
        Glide.with(requireContext())
            .load(requireContext().resources.getString(R.string.app_bar_image_url))
            .into(binding.image)

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
                                Log.e("Code", "Code")
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