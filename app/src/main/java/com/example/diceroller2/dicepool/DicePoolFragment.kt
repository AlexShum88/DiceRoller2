package com.example.diceroller2.dicepool

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.isVisible
import com.example.diceroller2.R
import com.example.diceroller2.databinding.FragmentDicePoolBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diceroller2.App
import com.example.diceroller2.model.Dice

class DicePoolFragment(

) : Fragment() {

    lateinit var binding: FragmentDicePoolBinding

    private val viewModel: DicePoolViewModel by viewModels { factory() }


    //dummy
    val color = R.color.purple_200
    val image = R.drawable.ic_start_dice
    val grain = 6
    //


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var colorSwitcher = false

        viewModel.colorSwitcher.observe(viewLifecycleOwner) {
            colorSwitcher = it.switch
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

            },
            object : PopUpAction {
                override fun changeColor(dice: Dice) {
//                viewModel.changeColor(dice)
                }

                override fun changeImage(dice: Dice) {
                    viewModel.changeImage(dice)
                }
            }
        )

        binding = FragmentDicePoolBinding.inflate(inflater, container, false)

        with(binding) {
            addButton.setOnClickListener { viewModel.addDice(6, color, image) }
            colorButton.setOnClickListener { viewModel.startChangeColorRegime() }
            doneButton.setOnClickListener { viewModel.endChangeColorRegime() }
        }

        viewModel.colorSwitcher.observe(viewLifecycleOwner) {
            binding.paletteFragment.isVisible = it.switch
            binding.selectColor.setBackgroundResource(it.color)
        }

        viewModel.dicesLD.observe(viewLifecycleOwner) {
            adapter.dices = it
        }

        binding.diceRecycler.adapter = adapter

//        binding.diceRecycler.layoutManager = LinearLayoutManager(requireContext())

        setLayoutManager()
        return binding.root
    }


    fun setLayoutManager() {

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


}