package com.example.diceroller2.model

object RollRegime {
    var rollAllPrevDIcesRegime = false

    fun changeRollRegime(){
        rollAllPrevDIcesRegime = !rollAllPrevDIcesRegime
    }
}