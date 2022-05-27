package com.example.diceroller2.dicepool

import android.view.Menu
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.example.diceroller2.R
import com.example.diceroller2.model.DiceRepository

//class PopUpForCreateButton(
//
//
//) {

    fun createGrainPopUpForAddDice(
        view: View,
        color: Int,
        image: Int,
        action: (Int, Int, Int) -> Unit
    ) {
        val popUp = PopupMenu(view.context, view)

        var i = 0

        val grainsID = mutableMapOf<Int, Int>()

        DiceRepository.GRAINS.forEach {
            i++
            popUp.menu.add(
                0, i, Menu.NONE, view.context.getString(R.string.grain_menu_text, it.toString())
            )
            grainsID[i] = it
        }

        popUp.setOnMenuItemClickListener {
            val grain = grainsID[it.itemId]!!
            action(grain, color, image)
            return@setOnMenuItemClickListener true
        }
        popUp.show()
    }
//}