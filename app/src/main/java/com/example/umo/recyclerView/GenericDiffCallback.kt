package com.example.umo.recyclerView

import androidx.recyclerview.widget.DiffUtil

class GenericDiffCallback<T>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]?.equals(newList[newItemPosition]) ?: false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]?.equals(newList[newItemPosition]) ?: false
    }
}