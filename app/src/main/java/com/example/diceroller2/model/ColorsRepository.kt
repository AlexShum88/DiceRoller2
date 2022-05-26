package com.example.diceroller2.model

import com.example.diceroller2.R

typealias DColorsListener = (colors: List<DColor>)->Unit

class ColorsRepository{

    private val colors = listOf<DColor>(
        DColor(R.color.black, false),
        DColor(R.color.teal_200, false),
        DColor(R.color.purple_500, false),
        DColor(R.color.purple_700, false),
        DColor(R.color.white, false),
        DColor(R.color.purple_200, false)
    )

    private val listeners = mutableSetOf<DColorsListener>()

    fun changeChecked(dColor: DColor): Boolean {
        val index = colors.indexOfFirst { dColor === it }
        if (index == -1) return false
        colors.forEach { it.isChecked = false }
        colors[index].isChecked = true
        notifyListeners()
        return true
    }


    fun getColors(): List<DColor> = colors

    fun addListener(listener: DColorsListener) {
        listeners.add(listener)
        listener.invoke(colors)
    }

    fun removeListener(listener: DColorsListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach {
            it.invoke(colors)
        }
    }
}
