package com.example.diceroller2.model.databaseRepository

import androidx.annotation.WorkerThread
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.DatabaseException
import com.example.diceroller2.model.database.DiceDao
import com.example.diceroller2.model.database.PresetDao
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map

class RoomPresetRepository(
    private val presetDao: PresetDao,
    private val diceDao: DiceDao
) {


    fun getPresetsWithDice(): Flow<List<Pair<PresetEntity, List<Dice>>>> =
        presetDao.getPresets().map { a->
            a.map{
                Pair(it, diceDao.getAllDicesByPresetId(it.id).map{ds->ds.toDice()})
            }
        }


    @WorkerThread
    suspend fun setPresetsWithDices(name: String, dices: List<Dice>) {
        val id = presetDao.insertPreset(PresetEntity.toPresetEntity(name))
        diceDao.insertDices(dices.map {
            DiceEntity.toDiceEntity(it, id.toInt())
        })
    }

    fun getPresetsNames(): Flow<List<String>>{
        return presetDao.getPresetsNames()
    }

    suspend fun getDices(presetName: String): List<Dice> {
        val presetId = presetDao.getPresetIdByName(presetName) ?: throw DatabaseException("cant get preset by name")
        return diceDao.getAllDicesByPresetId(presetId).map { it.toDice() }
    }

    suspend fun changePresetName(preset: PresetEntity) {
        presetDao.updatePresetName(preset)
    }

    suspend fun deletePreset(preset: PresetEntity){

        presetDao.deletePreset(preset)
        println("preset deleted")

    }
}



