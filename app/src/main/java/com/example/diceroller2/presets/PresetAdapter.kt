package com.example.diceroller2.presets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.databinding.PresetLineBinding
import com.example.diceroller2.model.Dice

interface PresetSaveActions {
    fun saveToBD(name: String, dices: List<Dice>)
}

class PresetAdapter(
    val presetSaveActions: PresetSaveActions
) : RecyclerView.Adapter<PresetAdapter.PresetViewHolder>(), View.OnClickListener {

    class PresetViewHolder(val binding: PresetLineBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    var listOfPresets = emptyList<Pair<String, List<Dice>>>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PresetLineBinding.inflate(inflater, parent, false)
        binding.savePresetButton.setOnClickListener { this }
        return PresetViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PresetViewHolder, position: Int) {
        val preset = listOfPresets[position]
        val name = preset.first
        val dices = preset.second
        var grainsViews = emptyList<Int>()
        holder.binding.constraintPresetLine.allViews
            .filter { it.tag == "diceView" }
            .toList()
            .forEach {
                holder.binding.constraintPresetLine.removeView(it)
            }

        grainsViews = dices.map {
            val grain = TextView(holder.itemView.context)
            grain.tag = "diceView"
            grain.textSize = 24f
            grain.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            grain.text = "D${it.grain}"
            grain.setTextColor(ContextCompat.getColor(holder.itemView.context, it.color))
            grain.id = ViewCompat.generateViewId()

            holder.binding.constraintPresetLine.addView(grain)
//            holder.binding.mainFlow.addView(grain)
            grain.id
        }
        holder.binding.mainFlow.referencedIds = grainsViews.toIntArray()


        with(holder.binding) {
            savePresetButton.tag = preset
            presetNameEdit.setText(name)


        }
    }

    override fun getItemCount(): Int = listOfPresets.size


    override fun onClick(v: View) {
        val preset = v.tag as Pair<String, List<Dice>>
        val name = preset.first
        val dices = preset.second
        presetSaveActions.saveToBD(name, dices)
    }
}