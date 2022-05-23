package com.example.diceroller2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diceroller2.databinding.ActivityMainBinding
import com.example.diceroller2.dicepool.DicePoolFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, DicePoolFragment())
            .commit()

    }
}