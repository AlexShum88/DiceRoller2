package com.example.diceroller2.model

import android.text.format.DateFormat
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

//typealias StatisticListener = (Map<String, List<Dice>>)->Unit
typealias StatisticListener = (List<StatisticObject>)->Unit

object Statistic {

    private val listeners = mutableSetOf<StatisticListener>()
    private val statistic = mutableListOf<StatisticObject>()

    fun getStatistic(): List<StatisticObject>{
        return statistic
    }

    fun thenDicesRoll(dices: List<Dice>){
        val date  = DateFormat.format("HH:mm:ss", Date().time).toString()
        statistic.add(StatisticObject(date, dices))
        notifyListeners()
    }

//    fun addListener(listener: StatisticListener){
//        listeners.add(listener)
//        listener.invoke(statistic)
//    }
//
//    fun removeListener(listener: StatisticListener){
//        listeners.remove(listener)
//    }

    fun listenStatistic():Flow<List<StatisticObject>> = callbackFlow {
        val listener: StatisticListener = {
            trySend(it)
        }
        listeners.add(listener)
        listener.invoke(statistic)
        awaitClose {
            listeners.remove(listener)
        }
    }.buffer(Channel.CONFLATED)

    fun notifyListeners(){
        listeners.forEach {
            it.invoke(statistic)
        }
    }

}