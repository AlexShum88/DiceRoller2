package com.example.diceroller2.model

import android.graphics.drawable.Drawable
import com.example.diceroller2.App
import com.example.diceroller2.R
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.random.Random

object DiceActions {

    fun rollDice(dice: Dice, repository: DiceMainRepository) {
        val grain = dice.grain
        val diceR = findDice(dice, repository)
        diceR.value = Random.nextInt(1, grain + 1)
        notifyRepositoryListeners(repository)

    }

    fun rollAllPreviousDices(dice: Dice, repository: DiceMainRepository) {
        val dices = repository.getDices()
        val index = dices.indexOfFirst { it === dice }
        if (index < 0) return
        (0..index).forEach {
            rollDice(dices[it], repository)
        }
        noActiveDicesToDefault(index, dices)
    }

    fun changeGrain(dice: Dice, newGrain: Int, repository: DiceMainRepository, currentPack: String) {
        val diceC = findDice(dice, repository)
        diceC.grain = newGrain

        val newImage = getRes("${currentPack}_d$newGrain", R.drawable::class.java)
        changeDiceImage(dice, newImage, repository)
        notifyRepositoryListeners(repository)
    }


    private fun <T> getRes(name: String, res: Class<T>): Int {
        return try {
            val f = res.getDeclaredField(name)
            f.getInt(f)
        } catch (e: Exception) {
            e.toString()
            -1
        }
    }

    fun noActiveDicesToDefault(index: Int, dices: List<Dice>) {

        (index + 1..dices.lastIndex).forEach {
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

    private fun findDice(dice: Dice, list: List<Dice>): Dice {
        return list.firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun notifyRepositoryListeners(repository: DiceMainRepository) {
        repository.notifyListeners()
    }


}