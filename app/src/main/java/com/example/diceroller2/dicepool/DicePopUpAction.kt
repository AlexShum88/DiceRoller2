package com.example.diceroller2.dicepool

import com.example.diceroller2.model.Dice

interface DicePopUpAction {
    fun removeDice(dice: Dice)
    fun changeGrain(dice: Dice, grain: Int)
    fun changeAllGrain(grain: Int)
    fun changeColorOfAllDices()
    //else
}