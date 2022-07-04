package com.example.diceroller2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.diceroller2.chooseColor.ChooseColorFragment
import com.example.diceroller2.dicepool.DicePoolFragment
import com.example.diceroller2.pack.PackFragment
import com.example.diceroller2.presets.PresetsFragment
import com.example.diceroller2.statistic.StatisticFragment

class TabAdapter(fragment: FragmentActivity, val pager: ViewPager2) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{PackFragment(pager)}
            1->{PresetsFragment(pager)}
            2->{DicePoolFragment()}
            3->{ChooseColorFragment(pager)}
            4->{StatisticFragment()}
            else -> { DicePoolFragment()}
        }
    }
}