package com.example.diceroller2.model

import android.os.Parcelable
import com.example.diceroller2.R
import kotlinx.parcelize.Parcelize

typealias SwitchColorListener = (switchColor: SwitchColor) -> Unit


class SwitchColor{
    private var _color: Int = R.color.purple_500
    var color: Int
        get() = _color
        set(value) {
            _color = value
            notifyListeners()
        }
    private var _switch: Boolean = false
    var switch: Boolean
        get() = _switch
        set(value) {
            _switch = value
            notifyListeners()
        }
    val listeners = mutableSetOf<SwitchColorListener>()

    fun addListener(listener: SwitchColorListener){
        listeners.add(listener)
        notifyListeners()
    }
    fun removeListener(listener: SwitchColorListener){
        listeners.remove(listener)
    }

    private fun notifyListeners(){
        listeners.forEach {
            it.invoke(this)
        }
    }

}
