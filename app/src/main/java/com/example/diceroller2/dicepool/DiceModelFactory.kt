package com.example.diceroller2.dicepool

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diceroller2.App

class DiceModelFactory(
    private val app: App
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DicePoolViewModel(app.resources) as T
    }

}

fun Fragment.factory() = DiceModelFactory(requireContext().applicationContext as App)