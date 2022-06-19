package com.example.diceroller2.presets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.databaseRepository.RoomPresetRepository
import kotlinx.coroutines.launch

class PresetsViewModel(
    private val roomPresetRepository: RoomPresetRepository
) : ViewModel() {

    val presetsList = roomPresetRepository.getPresetsNameWithDice().asLiveData()


    fun insertPreset(name: String, dices: List<Dice>) = viewModelScope.launch {
        roomPresetRepository.setPresetsWithDices(name, dices)
    }




    fun presetGetDices(preset: String) = viewModelScope.launch {
        var dss = roomPresetRepository.getDices(preset)
        println("dices from DB name ${dss}")
    }


//    fun insert(word: Word) = viewModelScope.launch {
//        repository.insert(word)
//    }
}