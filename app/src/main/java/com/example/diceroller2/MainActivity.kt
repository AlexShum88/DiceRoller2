package com.example.diceroller2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.diceroller2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigationUI()
    }

    fun setNavigationUI(){
        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    companion object{
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val CURRENT_DICE_PACK = "CURRENT_DICE_PACK"
        const val DEFAULT_DICE_PACK = "start"

    }

}