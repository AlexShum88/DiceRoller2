package com.example.diceroller2.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity

@Database(
    version = 1,
    entities = [
        DiceEntity::class,
        PresetEntity::class
    ]
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDiceDao():DiceDao
    abstract fun getPresetDao():PresetDao

    companion object{
        const val DATABASE_NAME = "database.db"
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
                INSTANCE = instance
                instance
            }
        }
    }
}