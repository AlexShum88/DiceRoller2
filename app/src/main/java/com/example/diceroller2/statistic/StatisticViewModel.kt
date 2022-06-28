package com.example.diceroller2.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diceroller2.model.DiceActions
import com.example.diceroller2.model.StatisticObject

class StatisticViewModel : ViewModel() {
    private val _statistic = MutableLiveData<List<StatisticObject>>()
    val statistic: LiveData<List<StatisticObject>> = _statistic


    fun create() {
        _statistic.value = DiceActions.stat.getStatistic()
    }





}