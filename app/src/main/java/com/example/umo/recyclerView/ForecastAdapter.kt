package com.example.umo.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.umo.R
import com.example.umo.data.ForecastAdapterData
import com.example.umo.databinding.ForecastDayItemBinding
import com.example.umo.databinding.ForecastTitleItemBinding

class ForecastAdapter(private val items: ArrayList<ForecastAdapterData>): RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    fun updateList(list: List<ForecastAdapterData>) {
        val diffCallback = GenericDiffCallback(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ForecastViewType.TITLE.ordinal -> {
                val binding = ForecastTitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleViewHolder(binding)
            }
            else -> {
                val binding = ForecastDayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ForecastDayViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ForecastAdapterData.Title -> ForecastViewType.TITLE.ordinal
            is ForecastAdapterData.DailyForecast -> ForecastViewType.DAILY_FORECAST.ordinal
        }
    }

    enum class ForecastViewType {
        TITLE,
        DAILY_FORECAST
    }

    abstract class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ForecastAdapterData)
    }


    class TitleViewHolder(private val binding: ForecastTitleItemBinding) : ViewHolder(binding) {
        override fun bind(item: ForecastAdapterData) {
            val resources = binding.root.context.resources
            val day = (item as ForecastAdapterData.Title).day
            binding.title.text = resources.getString(R.string.title_format_string, day.toString())
        }
    }

    class ForecastDayViewHolder(private val binding: ForecastDayItemBinding) : ViewHolder(binding) {
        override fun bind(item: ForecastAdapterData) {
            val data = item as ForecastAdapterData.DailyForecast
            val resources = binding.root.context.resources
            binding.currentTemp.text = resources.getString(R.string.temp_format, data.currentTemp.toInt().toString())
            binding.hiTemp.text = resources.getString(R.string.temp_format, data.hiTemp.toInt().toString())
            binding.lowTemp.text = resources.getString(R.string.temp_format, data.lowTemp.toInt().toString())
            binding.precipitation.text = resources.getString(R.string.precipitation_format, data.precipitation.toString())
            binding.timestamp.text = resources.getString(R.string.timestamp_format, data.timeStamp)
        }
    }
}