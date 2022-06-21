package com.example.diceroller2.presets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.databinding.PresetLineBinding
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.PresetEntity

interface PresetActions {
    fun saveToBD(preset: PresetEntity)
    fun addPreset(dices: List<Dice>)
    fun deletePreset(preset: PresetEntity)
}

class PresetAdapter(
    val presetSaveActions: PresetActions
) : RecyclerView.Adapter<PresetAdapter.PresetViewHolder>(), View.OnClickListener {

    class PresetViewHolder(val binding: PresetLineBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    var listOfPresets = emptyList<Pair<PresetEntity, List<Dice>>>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PresetLineBinding.inflate(inflater, parent, false)
        binding.savePresetButton.setOnClickListener(this)
        binding.addPresetToDesk.setOnClickListener(this)
        binding.deletePresetButton.setOnClickListener(this)
        return PresetViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PresetViewHolder, position: Int) {
        val presetWithDice = listOfPresets[position]
        val preset = presetWithDice.first
        val dices = presetWithDice.second


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
        
        with(holder.binding) {
            addPresetToDesk.tag = dices
            savePresetButton.tag = Pair(holder.binding.presetNameEdit, preset)
            deletePresetButton.tag = preset
            presetNameEdit.setText(preset.name)
            mainFlow.referencedIds = grainsViews.toIntArray()

        }
    }

    override fun getItemCount(): Int = listOfPresets.size


    override fun onClick(v: View) {
        when (v.tag) {
            is Pair<*, *> -> {
                val pair = v.tag as Pair<EditText, PresetEntity>
                val preset = pair.second
                preset.name = pair.first.text.toString()
                presetSaveActions.saveToBD(preset)
            }
            is List<*> -> {
                presetSaveActions.addPreset(v.tag as List<Dice>)
            }
            is PresetEntity -> {
                presetSaveActions.deletePreset(v.tag as PresetEntity)
            }
        }

    }
}