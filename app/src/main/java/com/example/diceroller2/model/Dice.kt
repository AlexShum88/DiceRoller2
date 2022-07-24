package com.example.diceroller2.model

import android.os.Parcelable
import com.example.diceroller2.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dice(
    var grain: Int,
    var value: Int,
    var image: String,
    var color: Int,
    var pack: String
): Parcelable{
    companion object{
        const val IMAGE_RES = ".png"
        const val DEFAULT_DICE_PACK = "start"
        const val DEFAULT_DICE_IMAGE = "start/6/1.png"
        const val DEFAULT_DICE_COLOR = R.color.white
        const val DEFAULT_DICE_GRAIN = 6
    }
}

