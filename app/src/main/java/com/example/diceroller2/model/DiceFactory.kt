package com.example.diceroller2.model

object DiceFactory {

    fun createDice(grain: Int, color: Int, image: String): Dice {
        return Dice(grain = grain, color = color, image = image, value = 1)
    }

    fun createDices(number: Int, color: Int, image: String, grain: Int): List<Dice>{
        return (1..number).map {
                createDice(grain = grain, color = color, image = image)
        }.toList()
    }

    fun createDicePack(exampleDice: Dice): List<Dice>{
        return DiceRepository.GRAINS.map {
            exampleDice.copy(grain = it)
        }
    }


}