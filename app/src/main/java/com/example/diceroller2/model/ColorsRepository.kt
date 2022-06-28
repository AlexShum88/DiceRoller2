package com.example.diceroller2.model

typealias DColorsListener = (colors: List<DColor>) -> Unit

object ColorsRepository {

    private val colors = ColorList.colors

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
