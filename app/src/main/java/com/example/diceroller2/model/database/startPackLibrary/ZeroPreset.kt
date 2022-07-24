package com.example.diceroller2.model.database.startPackLibrary

import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity
import com.example.diceroller2.model.database.startPackLibrary.StartPresetLibrary

class ZeroPreset() : StartPresetLibrary {
    override val id: Int
        get() = StartPresetLibrary.ID_ZERO
    override val presetName: String
        get() = "Zero"
    override val dices: List<DiceEntity> = emptyList()
}