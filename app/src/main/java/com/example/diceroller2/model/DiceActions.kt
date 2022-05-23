package com.example.diceroller2.model

import java.lang.IllegalArgumentException
import kotlin.random.Random

object DiceActions {

    fun rollDice(dice: Dice, repository: ConcreteRepository) {
        val grain = dice.grain
        val diceR = findDice(dice, repository)
        diceR.value = Random.nextInt(1, grain + 1)
        notifyRepositoryListeners(repository)

    }

    fun changeDiceColor(dice: Dice, color: Int, repository: ConcreteRepository) {
        val diceR = findDice(dice, repository)
        diceR.color = color
        notifyRepositoryListeners(repository)

    }

    fun changeDiceImage(dice: Dice, image: Int, repository: ConcreteRepository) {
        val diceR = findDice(dice, repository)
        diceR.image = image
        notifyRepositoryListeners(repository)

    }

    private fun findDice(dice: Dice, repository: ConcreteRepository): Dice {
        return repository.getDices().firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun notifyRepositoryListeners(repository: ConcreteRepository){
        repository.notifyListeners()
    }


}