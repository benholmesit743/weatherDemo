package com.example.milwaukeetool.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.milwaukeetool.data.CapitalData


class StartAdapterDiffCallback(private val oldList: List<CapitalData>, private val newList: List<CapitalData>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uid.equals(newList[newItemPosition].uid)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.equals(newItem)
    }
}