package com.example.diceroller2

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.diceroller2.chooseColor.ChooseColorViewModel
import com.example.diceroller2.dicepool.DicePoolViewModel
import com.example.diceroller2.dicepool.DiceViewModel2
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

class ModelFactory2(owner: SavedStateRegistryOwner):AbstractSavedStateViewModelFactory(owner, null){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when(modelClass){
//            DicePoolViewModel::class.java -> DicePoolViewModel(app.resources, app.switchColor ) as T
            DiceViewModel2::class.java -> DiceViewModel2(handle ) as T
//            ChooseColorViewModel::class.java -> ChooseColorViewModel(app.colors, app.switchColor) as T
            else -> throw IllegalArgumentException("unknown view model")
        }
    }
}

fun Fragment.factory() = ModelFactory(requireContext().applicationContext as App)