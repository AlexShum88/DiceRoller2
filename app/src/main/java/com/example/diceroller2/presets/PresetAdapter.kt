package com.example.diceroller2.presets

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.R
import com.example.diceroller2.databinding.DialogPresetChangeNameBinding
import com.example.diceroller2.databinding.PresetLineBinding
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.PresetEntity

interface PresetActions {
    fun setNewPresetName(preset: PresetEntity)
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
            grain.id
        }

        with(holder.binding) {
            addPresetToDesk.tag = dices
            deletePresetButton.tag = preset
            presetName.text = preset.name
            presetName.setOnClickListener{
                changeNameDialog(holder.itemView.context, preset)
            }
            mainFlow.referencedIds = grainsViews.toIntArray()

        }
    }

    override fun getItemCount(): Int = listOfPresets.size


    override fun onClick(v: View) {
        when (v.tag) {
            is List<*> -> {
                presetSaveActions.addPreset(v.tag as List<Dice>)
            }
            is PresetEntity -> {
                presetSaveActions.deletePreset(v.tag as PresetEntity)
            }
        }

    }

    fun changeNameDialog(context: Context, preset: PresetEntity){
        val inflater = LayoutInflater.from(context)
        val view = DialogPresetChangeNameBinding.inflate(inflater)
        view.dialogEditText.setText(preset.name)

        val listener = DialogInterface.OnClickListener { _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    preset.name = view.dialogEditText.text.toString()
                    presetSaveActions.setNewPresetName(preset)
                }
            }
        }

        AlertDialog.Builder(context)
            .setMessage("Change preset name")
            .setPositiveButton(R.string.save, listener)
            .setView(view.root)
            .create()
            .show()
    }
}