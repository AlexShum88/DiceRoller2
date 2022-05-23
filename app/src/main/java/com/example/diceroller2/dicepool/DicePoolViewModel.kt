package com.example.diceroller2.dicepool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diceroller2.R
import com.example.diceroller2.model.*

class DicePoolViewModel(
    private val repository: ConcreteRepository
) : ViewModel() {

    private val _dicesLD = MutableLiveData<List<Dice>>()
    val dicesLD: LiveData<List<Dice>> = _dicesLD

    private val diceListListener: DiceListListener = {
        _dicesLD.value = it
    }


    fun onCreate(){
        repository.addListener (diceListListener)
        if (repository.getDices().isEmpty()) {
            createStartDice()
        }
    }

    fun onDestroy(){
        repository.removeListener(diceListListener)
    }

    private fun createStartDice(){
        DiceFactory.createDices(6, R.color.teal_200, R.drawable.ic_start_dice,6)
            .forEach { repository.addDice(it) }
        _dicesLD.value = repository.getDices()
    }

    fun rollDice(dice: Dice){
        DiceActions.rollDice(dice, repository)
    }

    fun addDice(grain: Int, color: Int, image: Int){
        val dice = DiceFactory.createDice(grain, color, image)
        repository.addDice(dice)
    }

    fun changeColor(dice: Dice) {
        DiceActions.changeDiceColor(dice, R.color.black, repository)
    }

    fun changeImage(dice: Dice) {
        DiceActions.changeDiceImage(dice, R.drawable.ic_launcher_foreground, repository)
    }

}