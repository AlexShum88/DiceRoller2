package com.example.diceroller2.dicepool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.diceroller2.R
import com.example.diceroller2.model.*

class DicePoolViewModel(
    private val repository: ConcreteRepository,
    private val switchColor: SwitchColor
) : ViewModel() {

    private val _dicesLD = MutableLiveData<List<Dice>>()
    val dicesLD: LiveData<List<Dice>> = _dicesLD

    private val diceListListener: DiceListListener = {
        _dicesLD.value = it
    }


    private val _colorSwitcher = MutableLiveData<SwitchColor>()
    val colorSwitcher: LiveData<SwitchColor> = _colorSwitcher

    private val colorListener: SwitchColorListener = {
        _colorSwitcher.value = it
    }


    fun onCreate(){
        repository.addListener (diceListListener)
        if (repository.getDices().isEmpty()) {
            createStartDice()
        }


        switchColor.addListener(colorListener)

    }

    fun onDestroy(){
        repository.removeListener(diceListListener)

        switchColor.removeListener(colorListener)
    }

    private fun createStartDice(){
        DiceFactory.createDices(6, R.color.purple_700, R.drawable.ic_start_dice,6)
            .forEach { repository.addDice(it) }
        _dicesLD.value = repository.getDices()
    }

    fun rollDice(dice: Dice){
        DiceActions.rollDice(dice, repository)
    }

    fun rollPreviousDices(dice: Dice){
        DiceActions.rollAllPreviousDices(dice, repository)
    }

    fun addDice(grain: Int, color: Int, image: Int){
        val dice = DiceFactory.createDice(grain, color, image)
        repository.addDice(dice)
    }

    fun changeColor(dice: Dice) {
        // mock realisation
        DiceActions.changeDiceColor(dice, colorSwitcher.value?.color ?: R.color.white, repository)
        //todo call changeColorFragment

    }

    fun startChangeColorRegime(){
        switchColor.switch = true
    }

    fun endChangeColorRegime(){
        switchColor.switch = false
    }

    fun changeImage(dice: Dice) {
        DiceActions.changeDiceImage(dice, R.drawable.ic_launcher_foreground, repository)
    }

}