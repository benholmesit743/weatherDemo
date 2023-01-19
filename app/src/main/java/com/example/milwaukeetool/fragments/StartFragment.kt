package com.example.milwaukeetool.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.milwaukeetool.R
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.databinding.FragmentStartBinding
import com.example.milwaukeetool.recyclerView.StartAdapter
import com.example.milwaukeetool.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class StartFragment : Fragment() {
    private val viewModel by activityViewModel<MainViewModel>()
    private lateinit var binding: FragmentStartBinding

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
        val onClick: (data: CapitalData) -> Unit = {
            viewModel.currentItem = it
            Navigation.findNavController(binding.root).navigate(R.id.action_start_to_forecast)
            viewModel.getFiveDayForecastFromApi()
        }
        Glide.with(requireContext())
            .load(requireContext().resources.getString(R.string.app_bar_image_url))
            .into(binding.image);
        val adapter = StartAdapter(viewModel.getDataForStartFragment(), onClick)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }
}