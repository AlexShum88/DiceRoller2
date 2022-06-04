package com.example.diceroller2

import androidx.recyclerview.widget.DiffUtil
import com.example.diceroller2.model.Dice

class  DiffUtilComparator<T>(
    val oldItem: List<T>,
    val newItem: List<T>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] == newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] == newItem[newItemPosition]
    }


}
