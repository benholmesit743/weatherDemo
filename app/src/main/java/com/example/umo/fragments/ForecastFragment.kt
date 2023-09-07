package com.example.umo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.umo.R
import com.example.umo.databinding.FragmentForecastBinding
import com.example.umo.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding
    private val viewModel by activityViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getCurrentTemperatureFromDatabase()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backArrow.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        viewModel.forecastData.observe(viewLifecycleOwner) { zipCode ->
            zipCode?.let {
                @StringRes
                val degreeFormat: Int = if (it.unit == 1) R.string.temp_format_celsius else R.string.temp_format_fahrenheit
                binding.temperature.text = getString(degreeFormat, it.temperature)
                binding.timeStamp.text = it.timeStamp
                binding.image.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.good_weather))
            }
        }
    }
}