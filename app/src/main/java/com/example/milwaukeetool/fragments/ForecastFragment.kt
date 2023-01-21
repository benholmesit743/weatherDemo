package com.example.milwaukeetool.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.milwaukeetool.R
import com.example.milwaukeetool.data.toForecastAdapterData
import com.example.milwaukeetool.databinding.FragmentForecastBinding
import com.example.milwaukeetool.recyclerView.ForecastAdapter
import com.example.milwaukeetool.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding
    private val viewModel by activityViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getFiveDayForecastFromDatabase()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backArrow.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        val adapter = ForecastAdapter(ArrayList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        viewModel.forecastData.observe(viewLifecycleOwner) { capitalData ->
            capitalData?.let {
                if (binding.recyclerView.adapter == null) {
                    binding.recyclerView.adapter = ForecastAdapter(it.toForecastAdapterData())
                } else {
                    (binding.recyclerView.adapter as ForecastAdapter).updateList(it.toForecastAdapterData())
                }
                if (!binding.recyclerView.hasFixedSize()) binding.recyclerView.setHasFixedSize(true)
            }
        }
    }
}