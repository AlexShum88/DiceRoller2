package com.example.diceroller2.model


class DiceRepository: ConcreteRepository{

    private val dices = mutableListOf<Dice>()

    private val listeners = mutableSetOf<DiceListListener>()

    override fun addDice(dice: Dice){
        dices.add(dice)
        notifyListeners()
    }

    override fun removeDice(dice: Dice){
        dices.remove(dice)
        notifyListeners()
    }

    override fun getDices(): MutableList<Dice> = dices

    override fun addListener(listener: DiceListListener){
        listeners.add(listener)
        listener.invoke(dices)
    }

    override fun removeListener(listener: DiceListListener){
        listeners.remove(listener)
    }

    override fun notifyListeners(){
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