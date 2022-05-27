package com.example.diceroller2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dice(
    var grain: Int,
    var value: Int,
    var image: Int,
    var color: Int,
): Parcelable