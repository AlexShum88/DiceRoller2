package com.example.diceroller2.model

import android.text.format.DateFormat
import java.util.*

//typealias StatisticListener = (Map<String, List<Dice>>)->Unit
//typealias StatisticListener = (List<StatisticObject>)->Unit

data class StatisticObject(
    val date: String,
    val dices: List<Dice>
)

object Statistic {


    private val statistic = mutableListOf<StatisticObject>()

    fun getStatistic(): List<StatisticObject>{
        return statistic
    }

    fun thenDicesRoll(dices: List<Dice>){
        val date  = DateFormat.format("HH:mm:ss", Date().time).toString()
        statistic.add(StatisticObject(date, dices))
    }

}