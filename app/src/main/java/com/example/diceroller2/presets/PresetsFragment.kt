package com.example.diceroller2.presets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diceroller2.App
import com.example.diceroller2.databinding.FragmentPresetsBinding
import com.example.diceroller2.model.Dice

class PresetsFragment : Fragment() {



    private val viewModel: PresetsViewModel by viewModels { presetFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPresetsBinding.inflate(inflater, container, false)
        val adapter = PresetAdapter(
            object: PresetSaveActions{
                override fun saveToBD(name: String, dices: List<Dice>) {
                    viewModel.insertPreset(name, dices)

                }

            }
        )
        viewModel.presetsList.observe(viewLifecycleOwner){
            adapter.listOfPresets = it
        }

        binding.presetRecycle.adapter = adapter
        binding.presetRecycle.layoutManager = LinearLayoutManager(requireContext())

        binding.addPresetButton.setOnClickListener {
            val app = requireContext().applicationContext as App
            viewModel.insertPreset("name1" , app.resources.getDices())
//            viewModel.presetGetDices("name")

//            println("repo dices ${app.resources.getDices()}")
        }
        return binding.root
    }



}