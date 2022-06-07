package com.example.diceroller2.dicepool

import com.example.diceroller2.model.Dice

interface AdapterActions{
    fun onClickRoll(dice: Dice)
    fun setChangeAlphaRegime(): Boolean
}