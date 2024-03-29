package com.example.diceroller2.chooseColor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diceroller2.model.ColorsRepository
import com.example.diceroller2.model.DColor
import com.example.diceroller2.model.DColorsListener
import com.example.diceroller2.model.SwitchColor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChooseColorViewModel(
    private val switchColor: SwitchColor
) : ViewModel() {

    private val _colors = MutableLiveData<List<DColor>>()
    val colors: LiveData<List<DColor>> = _colors

    private val colorListener: DColorsListener = {
        _colors.value = it
    }

    fun onCreate() {
//        ColorsRepository.addListener(colorListener)

        viewModelScope.launch {
            ColorsRepository.listenCurrentColor().collect{
                _colors.value = it
            }
        }

        val dColor =
            ColorsRepository.getColors().firstOrNull { it.backgroundColor == switchColor.color }
                ?: return
        ColorsRepository.changeChecked(dColor)
    }

//    fun onDestroy() {
//        ColorsRepository.removeListener(colorListener)
//    }

    fun chooseColor(color: DColor) {
        if (ColorsRepository.changeChecked(color)) switchColor.color = color.backgroundColor
    }
}