package com.example.diceroller2.pack

import androidx.lifecycle.ViewModel
import com.example.diceroller2.model.PacksRepository

class PackViewModel : ViewModel() {

    val packsName = PacksRepository.NAMES
}