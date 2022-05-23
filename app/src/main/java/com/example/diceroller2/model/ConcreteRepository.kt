package com.example.diceroller2.model

interface ConcreteRepository: MainRepository {


    fun addDice(dice: Dice)

    fun removeDice(dice: Dice)

    fun getDices(): MutableList<Dice>

}