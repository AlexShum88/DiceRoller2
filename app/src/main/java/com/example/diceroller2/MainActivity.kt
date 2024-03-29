package com.example.diceroller2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller2.databinding.ActivityMainBinding
import com.example.diceroller2.model.Statistic
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTabs()


        // calling this activity's function to
        // use ActionBar utility methods
        val actionBar = supportActionBar

        // providing title for the ActionBar
        viewModel.totalScoreLD.observe(this){
            actionBar!!.title = "${getString(R.string.total_score)} ${it}"
        }


        // providing subtitle for the ActionBar
//        actionBar.subtitle = "   Design a custom Action Bar"

        // adding icon in the ActionBar
//        actionBar!!.setIcon(R.drawable.add_dice)//test

        // methods to display the icon in the ActionBar
        actionBar!!.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // methods to control the operations that will
    // happen when user clicks on the action buttons
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        helpDialog(binding.pager.currentItem)
        return super.onOptionsItemSelected(item)
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

    private fun helpDialog(currentTab: Int){
        val message =
            when(currentTab){
                TabAdapter.POSITION_OF_PACK -> R.string.pack_help
                TabAdapter.POSITION_OF_PRESETS -> R.string.preset_help
                TabAdapter.POSITION_OF_DICE_POOL -> R.string.dice_pool_help
                TabAdapter.POSITION_OF_CHOOSE_COLOR -> R.string.color_help
                TabAdapter.POSITION_OF_STATISTIC -> R.string.statistic_help
                else -> {R.string.help}
            }
        val dialog = AlertDialog.Builder(this)
            .setMessage(message)
            .create()
        dialog.show()
    }


    companion object {
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val CURRENT_DICE_PACK = "CURRENT_DICE_PACK"
        const val DEFAULT_DICE_PACK = "start"

    }
}