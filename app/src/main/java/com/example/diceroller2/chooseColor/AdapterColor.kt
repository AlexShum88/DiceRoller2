package com.example.diceroller2.chooseColor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.databinding.ColorViewBinding
import com.example.diceroller2.model.DColor

class AdapterColor(
    private val actionListener: ColorActionListener

) : RecyclerView.Adapter<AdapterColor.ColorHolder>(), View.OnClickListener {

    inner class ColorHolder(val binding: ColorViewBinding) : RecyclerView.ViewHolder(binding.root)

    var dColors = emptyList<DColor>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ColorViewBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ColorHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
        val dColor = dColors[position]
        holder.binding.root.tag = dColor
        holder.binding.colorChose.isVisible = dColor.isChecked
        holder.binding.colorView.setImageResource(dColor.backgroundColor)

    }

    override fun getItemCount(): Int = dColors.size

    override fun onClick(colorView: View) {
        actionListener.changeFocus(colorView.tag as DColor)
    }

}