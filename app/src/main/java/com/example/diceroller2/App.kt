package com.example.diceroller2

import android.app.Application
import androidx.room.Room
import com.example.diceroller2.model.*
import com.example.diceroller2.model.database.AppDatabase
import com.example.diceroller2.model.databaseRepository.RoomPresetRepository

class App: Application() {
    val switchColor = SwitchColor()
    val diceRepository = DiceRepository
    val colorRepository  = ColorsRepository
    val statistic = Statistic
    val rollRegime = RollRegime
    val database by lazy { AppDatabase.getDataBase(applicationContext)}
    val roomPresetRepository by lazy {   RoomPresetRepository(database.getPresetDao(), database.getDiceDao())}
}