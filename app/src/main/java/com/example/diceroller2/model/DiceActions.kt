package com.example.diceroller2.model

import java.lang.IllegalArgumentException
import kotlin.random.Random

object DiceActions {

    fun rollDice(dice: Dice, repository: DiceMainRepository) {
        val grain = dice.grain
        val diceR = findDice(dice, repository)
        diceR.value = Random.nextInt(1, grain + 1)
        notifyRepositoryListeners(repository)

    }

    fun rollAllPreviousDices(dice: Dice, repository: DiceMainRepository){
        val dices = repository.getDices()
        val index = dices.indexOfFirst { it === dice }
        if (index<0) return
        (0..index).forEach {
            rollDice(dices[it], repository)
        }
        noActiveDicesToDefault(index, dices)
    }

    fun noActiveDicesToDefault(index: Int, dices: List<Dice>){

        (index+1..dices.lastIndex).forEach{
            dices[it].value = 1
        }
    }

    fun changeDiceColor(dice: Dice, color: Int, repository: DiceMainRepository) {
        val diceR = findDice(dice, repository)
        diceR.color = color
        notifyRepositoryListeners(repository)

    }

    fun changeDiceImage(dice: Dice, image: Int, repository: DiceMainRepository) {
        val diceR = findDice(dice, repository)
        diceR.image = image
        notifyRepositoryListeners(repository)

    }

    private fun findDice(dice: Dice, repository: DiceMainRepository): Dice {
        return repository.getDices().firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun notifyRepositoryListeners(repository: DiceMainRepository){
        repository.notifyListeners()
    }


}