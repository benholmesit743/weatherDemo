package com.example.milwaukeetool.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.databinding.StartFragmentItemBinding


class StartAdapter(private val items: ArrayList<CapitalData>,
                   private val onClick: (data: CapitalData) -> Unit): RecyclerView.Adapter<StartAdapter.ViewHolder>() {

    fun updateList(list: List<CapitalData>) {
        val diffCallback = GenericDiffCallback(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StartFragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: StartFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CapitalData, onClick: (data: CapitalData) -> Unit) {
            binding.title.text = item.capital
            binding.subtitle.text = item.state
            binding.parent.setOnClickListener {
                onClick(item)
            }
        }
    }
}