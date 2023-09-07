package com.example.umo.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.umo.data.ZipCode
import com.example.umo.databinding.StartFragmentItemBinding


class StartAdapter(private val items: ArrayList<ZipCode>,
                   private val onClick: (data: ZipCode) -> Unit): RecyclerView.Adapter<StartAdapter.ViewHolder>() {

    fun updateList(list: List<ZipCode>) {
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
        fun bind(item: ZipCode, onClick: (data: ZipCode) -> Unit) {
            binding.title.text = item.zipCode
            binding.parent.setOnClickListener {
                onClick(item)
            }
        }
    }
}