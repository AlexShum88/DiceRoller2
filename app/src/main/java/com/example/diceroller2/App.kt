package com.example.diceroller2

import android.app.Application
import com.example.diceroller2.model.DiceRepository

class App: Application() {
    val resources = DiceRepository()
}