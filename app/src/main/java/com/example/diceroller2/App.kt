package com.example.diceroller2

import android.app.Application
import com.example.diceroller2.model.ColorsRepository
import com.example.diceroller2.model.DiceRepository
import com.example.diceroller2.model.SwitchColor

class App: Application() {
    val resources = DiceRepository()
    val switchColor = SwitchColor()
    val colors  = ColorsRepository()
}