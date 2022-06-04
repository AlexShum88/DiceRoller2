package com.example.diceroller2.model

import kotlin.random.Random


const val imageException = ".png"


object DiceActions {



    fun rollDice(dice: Dice, repository: DiceRepository) {
        val grain = dice.grain
        val diceR = findDice(dice, repository)
        diceR.value = Random.nextInt(1, grain + 1)
//        diceR.image = "${diceR.pack}/${diceR.grain}/${diceR.value}$imageException"
        changeDiceImage(diceR)
        notifyRepositoryListeners(repository)

    }

    private fun changeDiceImage(dice: Dice){
        dice.image = "${dice.pack}/${dice.grain}/${dice.value}$imageException"
    }

    fun rollAllPreviousDices(dice: Dice, repository: DiceRepository) {
        val dices = repository.getDices()
        val index = dices.indexOfFirst { it === dice }
        if (index < 0) return
        (0..index).forEach {
            rollDice(dices[it], repository)
        }
        noActiveDicesToDefault(index, dices, repository)
    }

    fun changeGrain(dice: Dice, newGrain: Int, repository: DiceRepository, currentPack: String) {
        val diceC = findDice(dice, repository)
        diceC.grain = newGrain
        diceC.value = 1
        changeDiceImage(diceC)
//        val newImage = "${diceR.pack}/${diceR.grain}/${diceR.value}$imageException"
//        val newImage = getRes("${currentPack}_d$newGrain", R.drawable::class.java)
//        changeDiceImage(dice, newImage, repository)
        notifyRepositoryListeners(repository)
    }

//    fun getRes(
//
//    ): String{
//
//    }

//    private fun <T> getRes(name: String, res: Class<T>): Int {
//        return try {
//            val f = res.getDeclaredField(name)
//            f.getInt(f)
//        } catch (e: Exception) {
//            e.toString()
//            -1
//        }
//    }

    fun noActiveDicesToDefault(index: Int, dices: List<Dice>, repository: DiceRepository) {

        (index + 1..dices.lastIndex).forEach {
            dices[it].value = 1
            changeDiceImage(dices[it])
        }
        notifyRepositoryListeners(repository)
    }

    fun changeDiceColor(dice: Dice, color: Int, repository: DiceRepository) {
        val diceR = findDice(dice, repository)
        diceR.color = color
        notifyRepositoryListeners(repository)

    }

//    fun changeDiceImage(dice: Dice, image: String, repository: DiceListenersRepository) {
//        val diceR = findDice(dice, repository)
//        diceR.image = image
//        notifyRepositoryListeners(repository)
//
//    }

    private fun findDice(dice: Dice, repository: DiceRepository): Dice {
        return repository.getDices().firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun findDice(dice: Dice, list: List<Dice>): Dice {
        return list.firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun notifyRepositoryListeners(repository: DiceRepository) {
        repository.notifyListeners()
    }


}