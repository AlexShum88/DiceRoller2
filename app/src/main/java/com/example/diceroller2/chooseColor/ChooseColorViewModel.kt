package com.example.diceroller2.chooseColor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diceroller2.model.ColorsRepository
import com.example.diceroller2.model.DColor
import com.example.diceroller2.model.DColorsListener
import com.example.diceroller2.model.SwitchColor

class ChooseColorViewModel(
    private val colorsRepository: ColorsRepository,
    private val switchColor: SwitchColor
) : ViewModel() {

    private val _colors = MutableLiveData<List<DColor>>()
    val colors: LiveData<List<DColor>> = _colors

    private val colorListener: DColorsListener = {
        _colors.value = it
    }

    fun onCreate() {
        colorsRepository.addListener(colorListener)

        val dColor = colorsRepository.getColors().firstOrNull{ it.backgroundColor == switchColor.color} ?: return
        colorsRepository.changeChecked(dColor)
    }

    fun onDestroy() {
        colorsRepository.removeListener(colorListener)
    }

    fun chooseColor(color: DColor) {
        if(colorsRepository.changeChecked(color)) switchColor.color = color.backgroundColor
    }
}