package com.example.diceroller2.presets

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diceroller2.App
import com.example.diceroller2.ModelFactory
import com.example.diceroller2.model.databaseRepository.RoomPresetRepository

class PresetViewModelFactory (
    private val app: App
        ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){
            PresetsViewModel::class.java -> PresetsViewModel(app.roomPresetRepository) as T
        else -> throw IllegalArgumentException()
        }
    }

}

fun Fragment.presetFactory() = PresetViewModelFactory(requireContext().applicationContext as App)