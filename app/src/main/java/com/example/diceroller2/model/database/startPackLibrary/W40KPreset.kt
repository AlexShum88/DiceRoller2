package com.example.diceroller2.model.database.startPackLibrary

import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity

class W40KPreset:StartPresetLibrary {
    override val id: Int
        get() = StartPresetLibrary.ID_W40K
    override val presetName: String
        get() = "Warhammer 40K"
    override val dices: List<DiceEntity>
        get() = listOf(
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
            DiceEntity(0, id, 6, Dice.DEFAULT_DICE_IMAGE, Dice.DEFAULT_DICE_COLOR, Dice.DEFAULT_DICE_PACK),
        )
}