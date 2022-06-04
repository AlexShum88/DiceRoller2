package com.example.diceroller2

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller2.chooseColor.ChooseColorFragment
import com.example.diceroller2.databinding.ActivityMainBinding
import com.example.diceroller2.dicepool.DicePoolFragment

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportFragmentManager.beginTransaction()
////            .replace(R.id.fragment_container_view_tag, DicePoolFragment())
//            .replace(R.id.fragment_container_view_tag, DicePoolFragment())
//            .commit()
    }

    companion object{
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val CURRENT_DICE_PACK = "CURRENT_DICE_PACK"

    }

}