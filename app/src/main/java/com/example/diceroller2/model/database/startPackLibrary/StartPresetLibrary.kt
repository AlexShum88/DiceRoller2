package com.example.diceroller2.model.database.startPackLibrary

import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity

interface StartPresetLibrary{
    val id: Int
    val presetName: String
    val preset: PresetEntity
        get() = PresetEntity(id,presetName)
    val dices: List<DiceEntity>
    companion object{
        const val ID_ZERO = 1
        const val ID_APOCALYPSE_WORLD = 2
        const val ID_5E = 3
        const val ID_W40K = 4
        const val ID_WARCRY = 5

    }
}
