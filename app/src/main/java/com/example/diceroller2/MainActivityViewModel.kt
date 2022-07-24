package com.example.diceroller2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diceroller2.model.Statistic
import com.example.diceroller2.model.StatisticListener
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    private val _totalScoreLD: MutableLiveData<Int> = MutableLiveData<Int>()
    val totalScoreLD: LiveData<Int> = _totalScoreLD

    init{
        viewModelScope.launch {
            Statistic.listenTotalScore().collect{
                    _totalScoreLD.value = it
            }
        }
    }
}