package com.example.diceroller2.dicepool

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.diceroller2.App

class DiceModelFactory(
    private val app: App,

): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DicePoolViewModel(app.resources, app.switchColor ) as T
    }

}

fun Fragment.factory() = DiceModelFactory(requireContext().applicationContext as App)