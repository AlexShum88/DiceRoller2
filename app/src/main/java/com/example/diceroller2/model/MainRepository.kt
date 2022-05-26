package com.example.diceroller2.model

import android.location.GnssAntennaInfo

typealias DiceListListener = (dices: List<Dice>) -> Unit



interface MainRepository {

    fun addListener(listener: DiceListListener)

    fun removeListener(listener: DiceListListener)

    fun notifyListeners()
}