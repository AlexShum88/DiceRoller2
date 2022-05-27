package com.example.diceroller2.model

import android.os.Parcelable
import com.example.diceroller2.R
import kotlinx.parcelize.Parcelize

typealias SwitchColorListener = (switchColor: SwitchColor) -> Unit

@Parcelize
class SwitchColor: Parcelable{
    private var _color: Int = R.color.purple_500
    var color: Int
        get() = _color
        set(value) {
            _color = value
            notfListeners()
        }
    private var _switch: Boolean = false
    var switch: Boolean
        get() = _switch
        set(value) {
            _switch = value
            notfListeners()
        }
    val listeners = mutableSetOf<SwitchColorListener>()

    fun addListener(listener: SwitchColorListener){
        listeners.add(listener)
        notfListeners()
    }
    fun removeListener(listener: SwitchColorListener){
        listeners.remove(listener)
    }

    fun notfListeners(){
        listeners.forEach {
            it.invoke(this)
        }
    }

}
