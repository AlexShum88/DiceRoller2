package com.example.diceroller2.dicepool

import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import com.example.diceroller2.model.Dice
import java.lang.IllegalArgumentException

interface PopUpAction {
    fun changeColor(dice: Dice)
    fun changeImage(dice: Dice)
    //else
}

class PopUp(
    private val view: View,
    private val actions: PopUpAction
) {

    fun createPop() {
        val popUp = PopupMenu(view.context, view)
        val dice = if(view.tag is Dice ) view.tag as Dice else throw IllegalArgumentException("wrong view tag in pop")
        popUp.menu.add(0, 1, Menu.NONE, "Change color")
        popUp.menu.add(0, 2, Menu.NONE, "Change image")

        popUp.setOnMenuItemClickListener {
            when(it.itemId){
                CHANGE_COLOR -> {
                    actions.changeColor(dice)
                }

                CHANGE_IMAGE -> {
                    actions.changeImage(dice)
                }
                else -> {}
            }
            return@setOnMenuItemClickListener true
        }
        popUp.show()
    }

    companion object {
        const val CHANGE_COLOR = 1
        const val CHANGE_IMAGE = 2
    }
}