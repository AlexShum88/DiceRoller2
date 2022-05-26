package com.example.diceroller2

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diceroller2.chooseColor.ChooseColorViewModel
import com.example.diceroller2.dicepool.DicePoolViewModel
import java.lang.IllegalArgumentException

class ModelFactory(
    private val app: App,

): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){
            DicePoolViewModel::class.java -> DicePoolViewModel(app.resources, app.switchColor ) as T
            ChooseColorViewModel::class.java -> ChooseColorViewModel(app.colors, app.switchColor) as T
            else -> throw IllegalArgumentException("unknown view model")
        }
    }

}

fun Fragment.factory() = ModelFactory(requireContext().applicationContext as App)