package com.example.diceroller2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.diceroller2.databinding.ActivityMainBinding
import com.example.diceroller2.databinding.FragmentDicePoolBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(){

//    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = TabAdapter(this, binding.pager)
        binding.pager.setCurrentItem(2, false)
        TabLayoutMediator(binding.tabLayout, binding.pager){ tab, position ->
            when(position){
                0 ->{
                    tab.text = this.getString(R.string.pack)
                }
                1 ->{
                    tab.text = this.getString(R.string.presets)
                }
                2 ->{
                    tab.text = this.getString(R.string.dice_pool)
                }
                3 ->{
                    tab.text = this.getString(R.string.choose_color)
                }
                4 ->{
                    tab.text = this.getString(R.string.statistic)
                }
            }
        }.attach()
//        setNavigationUI()
    }

//    fun setNavigationUI(){
//        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
//        navController = navHost.navController
//        NavigationUI.setupActionBarWithNavController(this, navController)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


    companion object{
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val CURRENT_DICE_PACK = "CURRENT_DICE_PACK"
        const val DEFAULT_DICE_PACK = "start"

    }

}