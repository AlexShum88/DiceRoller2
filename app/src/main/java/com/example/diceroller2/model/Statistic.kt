package com.example.diceroller2.model

import android.text.format.DateFormat
import java.util.*

//typealias StatisticListener = (Map<String, List<Dice>>)->Unit
//typealias StatisticListener = (List<StatisticObject>)->Unit

data class StatisticObject(
    val date: String,
    val dices: List<Dice>
)

class Statistic {

//    private val statistic = mutableMapOf<String, List<Dice>>()
    private val statistic = mutableListOf<StatisticObject>()

    fun getStatistic(): List<StatisticObject>{
        return statistic
    }

    fun thenDicesRoll(dices: List<Dice>){
        val date  = DateFormat.format("HH:mm:ss", Date().time).toString()
        statistic.add(StatisticObject(date, dices))
//        statistic[date] = dices
//        statistic.keys.forEach { println("result = $it: ${statistic[it]}") }
//        notifyListeners()
    }

//    private val listeners = mutableSetOf<StatisticListener>()
//
//    fun addListener(statisticListener: StatisticListener){
//        listeners.add(statisticListener)
//
//    }
//
//    fun removeListener(statisticListener: StatisticListener){
//        listeners.remove(statisticListener)
//    }
//
//    private fun notifyListeners(){
//        listeners.forEach {
//            it.invoke(statistic)
//        }
//    }






}