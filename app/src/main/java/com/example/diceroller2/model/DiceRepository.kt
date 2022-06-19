package com.example.diceroller2.model

typealias DiceListListener = (dices: List<Dice>) -> Unit

class DiceRepository{

    //!!!!! huinia

    //!!!!!

    private val dices = mutableListOf<Dice>()

    private val listeners = mutableSetOf<DiceListListener>()

    fun addDice(dice: Dice){
        dices.add(dice)
        notifyListeners()
    }


    fun removeDice(dice: Dice){
        dices.remove(dice)
        notifyListeners()
    }

    fun getDices(): MutableList<Dice> = dices

    fun addListener(listener: DiceListListener){
        listeners.add(listener)
        listener.invoke(dices)
    }

    fun removeListener(listener: DiceListListener){
        listeners.remove(listener)
    }

    fun notifyListeners(){
        listeners.forEach {
            it.invoke(dices)
        }
    }

    companion object {
        val GRAINS = listOf<Int>(
            2,
            4,
            6,
            8,
            10,
            12,
            20,
        )
    }
}