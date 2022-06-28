package com.example.diceroller2.dicepool

import android.view.Menu
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.example.diceroller2.R
import com.example.diceroller2.model.DiceFactory
import com.example.diceroller2.model.DiceGrains
import com.example.diceroller2.model.DiceRepository

//class PopUpForCreateButton(
//
//
//) {

    fun createGrainPopUpForAddDice(
        view: View,
        color: Int,
        pack: String,
        action: (Int, Int, String) -> Unit
    ) {
        val popUp = PopupMenu(view.context, view)
        var i = 0
        val grainsID = mutableMapOf<Int, Int>()
        DiceGrains.GRAINS.forEach {
            i++
            popUp.menu.add(
                0, i, Menu.NONE, view.context.getString(R.string.grain_menu_text, it.toString())
            )
            grainsID[i] = it
        }
        popUp.setOnMenuItemClickListener {
            val grain = grainsID[it.itemId]!!
            action(grain, color, pack)
            return@setOnMenuItemClickListener true
        }
        popUp.show()
    }
//}