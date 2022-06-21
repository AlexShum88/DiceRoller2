package com.example.diceroller2.model.database

import androidx.room.*
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.database.entities.DiceEntity
import com.example.diceroller2.model.database.entities.PresetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PresetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPreset(preset: PresetEntity): Long

    @Query("Select * from presets")
    fun getPresets():Flow<List<PresetEntity>>

//    @Query("select presets.name as name, dice_table.* from presets join dice_table on  presets.id = dice_table.preset_id ")
//    fun getPresetsWithDices(): Flow<Map<PresetEntity, List<DiceEntity>>>

    @Delete
    suspend fun deletePreset(preset: PresetEntity)


    @Query("Select id from presets where name = :presetName")
    suspend fun getPresetIdByName(presetName: String): Int?

    @Query("Select name from presets where name = :presetId")
    fun getPresetNameById(presetId:Int): String?

    @Query("Select * from presets where name = :presetName")
    fun getPresetByName(presetName: String): PresetEntity?

    @Update()
    suspend fun updatePresetName(preset: PresetEntity)
}