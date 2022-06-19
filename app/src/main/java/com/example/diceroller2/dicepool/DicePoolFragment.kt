package com.example.diceroller2.dicepool

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diceroller2.MainActivity
import com.example.diceroller2.R
import com.example.diceroller2.databinding.FragmentDicePoolBinding
import com.example.diceroller2.factory
import com.example.diceroller2.model.Dice

class DicePoolFragment(

) : Fragment() {

    lateinit var binding: FragmentDicePoolBinding

    private val viewModel: DicePoolViewModel by viewModels { factory() }


    //dummy
    val color = R.color.purple_200
    val image = "start/6/1.png"
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

        val preferences = this.requireActivity().getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
        println("FFFF"+preferences.contains(MainActivity.CURRENT_DICE_PACK))

        var currentPack = preferences.getString(MainActivity.CURRENT_DICE_PACK, "start")
                            preferences.edit()
                        .putString(MainActivity.CURRENT_DICE_PACK, "start")
                        .apply()


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

                override fun setChangeAlphaRegime(): Boolean {
                    return colorSwitcher
                }

            },
            object : DicePopUpAction {
                override fun removeDice(dice: Dice) {
                    viewModel.removeDice(dice)
                }

                override fun changeGrain(dice: Dice, grain: Int) {
                    viewModel.changeGrain(dice, grain, currentPack!!)
                    /////////////////////
//                    preferences.edit()
//                        .putString(MainActivity.CURRENT_DICE_PACK, "dodik")
//                        .apply()
                    /////////////////////
                }

                override fun changeAllGrain(grain: Int) {
                    viewModel.changeAllGrainForAll(grain, currentPack!!)
                }


            }
        )

        binding = FragmentDicePoolBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener {
//                createGrainPopUpForAddDice(it, color, image, viewModel::addDice)
                //test statistic
//                findNavController().navigate(R.id.action_dicePoolFragment_to_statisticFragment)
            findNavController().navigate(R.id.action_dicePoolFragment_to_presetsFragment)


        }


        setColorButton(binding.colorButton)

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


    fun createDice(grain: Int){

        viewModel.addDice(grain, color, image)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    fun setColorButton(button: Button){

        button.setOnLongClickListener{
            findNavController().navigate(R.id.action_dicePoolFragment_to_chooseColorFragment)
            return@setOnLongClickListener true
        }

        viewModel.colorSwitcher.observe(viewLifecycleOwner) {

//            button.setBackgroundColor(binding.root.context.getColor(R.color.white))

            if (it.switch){
                button.setBackgroundColor(binding.root.context.getColor(it.color))
                Toast.makeText(requireContext(), "Change Color regime ON", Toast.LENGTH_LONG).show()
            }
            else{
                button.setBackgroundColor(binding.root.context.getColor(R.color.white))
                Toast.makeText(requireContext(), "Change Color regime OFF", Toast.LENGTH_LONG).show()

            }
            button.setOnClickListener { _->
                if(!it.switch) {
                    viewModel.startChangeColorRegime()
                    //todo change button image then change regime

                }
                else {
                    viewModel.endChangeColorRegime()
                }
            }
        }


    }

}