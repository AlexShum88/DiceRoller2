package com.example.diceroller2.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diceroller2.model.DiceActions
import com.example.diceroller2.model.Statistic
import com.example.diceroller2.model.StatisticListener
import com.example.diceroller2.model.StatisticObject

class StatisticViewModel : ViewModel() {
    private val _statistic = MutableLiveData<List<StatisticObject>>()
    val statistic: LiveData<List<StatisticObject>> = _statistic
    val statisticListener: StatisticListener = {
        _statistic.value = it
    }

    fun create() {
        Statistic.addListener(statisticListener)
    }

    fun destroy() {
        Statistic.removeListener(statisticListener)
    }





}