package com.example.diceroller2.model.database.startPackLibrary

import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity

class ApocalypseWorldPreset: StartPresetLibrary {
    override val id = StartPresetLibrary.ID_APOCALYPSE_WORLD
    override val presetName: String
        get() = "Apocalypse world"
    override val dices: List<DiceEntity>
        get() = listOf<DiceEntity>(
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
        )
}