package com.example.diceroller2.model

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow

typealias DColorsListener = (colors: List<DColor>) -> Unit

object ColorsRepository {
    //    var colorSwitcher = true
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

    //    fun addListener(listener: DColorsListener) {
//        listeners.add(listener)
//        listener.invoke(colors)
//    }
//
//    fun removeListener(listener: DColorsListener) {
//        listeners.remove(listener)
//    }
    fun listenCurrentColor(): Flow<List<DColor>> = callbackFlow {
        val listener: DColorsListener = {
            trySend(it)
        }
        listeners.add(listener)

        awaitClose {
            listeners.remove(listener)
        }
    }.buffer(Channel.CONFLATED)


    private fun notifyListeners() {
        listeners.forEach {
            it.invoke(colors)
        }
    }
}
