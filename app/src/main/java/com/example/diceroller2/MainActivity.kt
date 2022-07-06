package com.example.diceroller2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTabs()
    }

    private fun setTabs() {
        binding.pager.adapter = TabAdapter(this, binding.pager)
        binding.pager.setCurrentItem(TabAdapter.POSITION_OF_DICE_POOL, false)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                TabAdapter.POSITION_OF_PACK -> {
                    tab.text = this.getString(R.string.pack)
                }
                TabAdapter.POSITION_OF_PRESETS -> {
                    tab.text = this.getString(R.string.presets)
                }
                TabAdapter.POSITION_OF_DICE_POOL -> {
                    tab.text = this.getString(R.string.dice_pool)
                }
                TabAdapter.POSITION_OF_CHOOSE_COLOR -> {
                    tab.text = this.getString(R.string.choose_color)
                }
                TabAdapter.POSITION_OF_STATISTIC -> {
                    tab.text = this.getString(R.string.statistic)
                }
            }
        }.attach()
    }

    companion object {
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val CURRENT_DICE_PACK = "CURRENT_DICE_PACK"
        const val DEFAULT_DICE_PACK = "start"

    }
}