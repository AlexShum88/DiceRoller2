package com.example.diceroller2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.diceroller2.chooseColor.ChooseColorFragment
import com.example.diceroller2.dicepool.DicePoolFragment
import com.example.diceroller2.pack.PackFragment
import com.example.diceroller2.presets.PresetsFragment
import com.example.diceroller2.statistic.StatisticAdapter
import com.example.diceroller2.statistic.StatisticFragment

class TabAdapter(fragment: FragmentActivity, private val pager: ViewPager2) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            POSITION_OF_PACK->{PackFragment()}
            POSITION_OF_PRESETS->{PresetsFragment()}
            POSITION_OF_DICE_POOL->{DicePoolFragment()}
            POSITION_OF_CHOOSE_COLOR->{ChooseColorFragment()}
            POSITION_OF_STATISTIC->{StatisticFragment()}
            else -> { DicePoolFragment()}
        }
    }

    companion object{
        const val POSITION_OF_PACK = 0
        const val POSITION_OF_PRESETS = 1
        const val POSITION_OF_DICE_POOL = 2
        const val POSITION_OF_CHOOSE_COLOR = 3
        const val POSITION_OF_STATISTIC = 4
    }
}