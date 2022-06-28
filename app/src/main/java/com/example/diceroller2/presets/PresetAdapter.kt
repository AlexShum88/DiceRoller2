package com.example.diceroller2.presets

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.R
import com.example.diceroller2.databinding.DialogPresetChangeNameBinding
import com.example.diceroller2.databinding.PresetLineBinding
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.DiceRepository
import com.example.diceroller2.model.database.entities.PresetEntity

interface PresetActions {
    fun setNewPresetName(preset: PresetEntity)
    fun addPreset(dices: List<Dice>)
    fun deletePreset(preset: PresetEntity)
}

class PresetAdapter(
    private val presetSaveActions: PresetActions
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
        val context = holder.itemView.context

        var grainsViews = emptyList<Int>()
        holder.binding.constraintPresetLine.allViews
            .filter { it.tag == TAG_GRAIN_VIEW }
            .toList()
            .forEach {
                holder.binding.constraintPresetLine.removeView(it)
            }

        grainsViews = dices.map {
            val grain = TextView(holder.itemView.context)
            grain.tag = TAG_GRAIN_VIEW
            grain.textSize = context.resources.getDimension(R.dimen.text_size_in_generated_view) / TEXT_SIZE_CORRECTION_DIVIDER
            grain.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            grain.text = context.getString(R.string.grain_menu_text, it.grain.toString())
            grain.setTextColor(ContextCompat.getColor(context, it.color))
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

    private fun changeNameDialog(context: Context, preset: PresetEntity){
        val inflater = LayoutInflater.from(context)
        val view = DialogPresetChangeNameBinding.inflate(inflater)
        view.dialogEditText.setText(preset.name)

        val listOfExistPresets = listOfPresets.map {
            it.first.name
        }

        val dialog = AlertDialog.Builder(context)
            .setMessage(context.getString(R.string.change_preset_name))
            .setPositiveButton(R.string.save, null)
            .setNegativeButton(R.string.dismiss, null)
            .setView(view.root)
            .create()
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val name = view.dialogEditText.text.toString().trim()
                if (name.isEmpty()) {
                    view.dialogEditText.error = context.getString(R.string.enter_preset_name)
                    return@setOnClickListener
                }

                if (!listOfExistPresets.contains(name)) {
                    preset.name = view.dialogEditText.text.toString()
                    presetSaveActions.setNewPresetName(preset)
                } else {
                    view.dialogEditText.error = context.getString(R.string.such_name_exist)
                    return@setOnClickListener
                }
                dialog.dismiss()
            }
        }
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.show()
    }

    companion object{
        const val TAG_GRAIN_VIEW = "grainView"
        const val TEXT_SIZE_CORRECTION_DIVIDER = 4
    }
}