package com.example.diceroller2.model.databaseRepository

import androidx.annotation.WorkerThread
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.DatabaseException
import com.example.diceroller2.model.database.DiceDao
import com.example.diceroller2.model.database.PresetDao
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity
import com.example.diceroller2.presets.PresetAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.NullPointerException

class RoomPresetRepository(
    val presetDao: PresetDao,
    val diceDao: DiceDao
) {

        fun getPresetsNameWithDice(): Flow<List<Pair<String, List<Dice>>>> =
        presetDao.getPresets().map { a->
            a.map {
                Pair(it.name, diceDao.getAllDicesByPresetId(it.id).map { ds -> ds.toDice() })
            }
        }

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

    suspend fun getDices(presetId: Int): List<Dice> {
        return diceDao.getAllDicesByPresetId(presetId).map { it.toDice() }
    }

    suspend fun getDices(presetName: String): List<Dice> {
        val presetId = presetDao.getPresetIdByName(presetName) ?: throw DatabaseException("cant get preset by name")
        return diceDao.getAllDicesByPresetId(presetId).map { it.toDice() }
    }

    suspend fun changePresetName(preset: PresetEntity) {
        presetDao.updatePresetName(preset)

    }
}



