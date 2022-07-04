package com.example.diceroller2.dicepool

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diceroller2.MainActivity
import com.example.diceroller2.R
import com.example.diceroller2.databinding.FragmentDicePoolBinding
import com.example.diceroller2.factory
import com.example.diceroller2.model.Dice
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DicePoolFragment(

) : Fragment() {

    lateinit var binding: FragmentDicePoolBinding
    private val viewModel: DicePoolViewModel by viewModels { factory() }
    private var switchColor: Int = 0
    private lateinit var preferences: SharedPreferences
    private lateinit var currentPack: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = this.requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDicePoolBinding.inflate(inflater, container, false)
        setCurrentPack()
        var colorSwitcher = false
        viewModel.colorSwitcher.observe(viewLifecycleOwner) {
            colorSwitcher = it.switch
            switchColor = it.color
        }
        val adapter = DiceAdapter(this,

            object : AdapterActions {

                override fun onClickRoll(dice: Dice) {
                    if (colorSwitcher) {
                        viewModel.changeColor(dice)
                    } else {
                        viewModel.rollPreviousDices(dice)
                    }
                }

                override fun setChangeAlphaRegime(): Boolean {
                    return colorSwitcher
                }
            },

            object : DicePopUpAction {
                override fun removeDice(dice: Dice) {
                    viewModel.removeDice(dice)
                }

                override fun changeGrain(dice: Dice, grain: Int) {
                    viewModel.changeGrain(dice, grain)
                }

                override fun changeAllGrain(grain: Int) {
                    viewModel.changeGrainForAllDices(grain)
                }


            }
        )

        viewModel.dicesLD.observe(viewLifecycleOwner) {
            adapter.dices = it
        }
        binding.diceRecycler.adapter = adapter
        setAddButton(binding.addButton)
        setColorButton(binding.colorButton)

//        binding.diceRecycler.layoutManager = LinearLayoutManager(requireContext())
        setLayoutManager()
        return binding.root
    }


    private fun setLayoutManager() {
        binding.diceRecycler.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.diceRecycler.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.diceRecycler.width
                val itemWidth = resources.getDimensionPixelSize(R.dimen.dice_size)
                val columns = width / itemWidth
                binding.diceRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    private fun setColorButton(button: FloatingActionButton) {
        viewModel.colorSwitcher.observe(viewLifecycleOwner) {
            button.backgroundTintList =
                ColorStateList.valueOf(binding.root.context.getColor(it.color))
            if (it.switch) {
                button.setImageResource(R.drawable.paint_dice_icon)
            } else {
                button.setImageResource(R.drawable.change_color_icon)
            }
            button.setOnClickListener { _ ->
                if (!it.switch) {
                    viewModel.startChangeColorRegime()
                } else {
                    viewModel.endChangeColorRegime()
                }
            }
        }
    }

    private fun setCurrentPack() {
        currentPack =
            preferences.getString(MainActivity.CURRENT_DICE_PACK, MainActivity.DEFAULT_DICE_PACK)
                ?: MainActivity.DEFAULT_DICE_PACK
    }

    private fun setAddButton(button: FloatingActionButton) {
        button.setOnClickListener {
            setCurrentPack()
            createGrainPopUpForAddDice(it, switchColor, currentPack, viewModel::addDice)
        }
    }

}