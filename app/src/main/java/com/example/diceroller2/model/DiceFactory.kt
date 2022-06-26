package com.example.diceroller2.model

object DiceFactory {

    fun createDice(grain: Int, color: Int, image: String, pack: String): Dice {
        return Dice(grain = grain, color = color, image = image, value = 1, pack = pack)
    }

    fun createDices(number: Int, color: Int, image: String, grain: Int, pack: String): List<Dice>{
        return (1..number).map {
                createDice(grain = grain, color = color, image = image, pack = pack)
        }.toList()
    }

    fun createDicePack(exampleDice: Dice): List<Dice>{
        return DiceRepository.GRAINS.map {
            exampleDice.copy(grain = it)
        }
    }


}