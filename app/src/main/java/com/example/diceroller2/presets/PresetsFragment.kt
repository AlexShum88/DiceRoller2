package com.example.diceroller2.presets

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.diceroller2.MainActivity
import com.example.diceroller2.R
import com.example.diceroller2.databinding.DialogPresetChangeNameBinding
import com.example.diceroller2.databinding.FragmentPresetsBinding
import com.example.diceroller2.model.DiceRepository

class PresetsFragment(val pager: ViewPager2) : Fragment() {

    private val viewModel: PresetsViewModel by viewModels { presetFactory() }
    lateinit var preferences: SharedPreferences
    lateinit var currentPack: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = this.requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPresetsBinding.inflate(inflater, container, false)
        val adapter by lazy {
            PresetAdapter(
                viewModel::changePresetName,
                {

                    currentPack =
                        preferences.getString(MainActivity.CURRENT_DICE_PACK, MainActivity.DEFAULT_DICE_PACK)
                            ?: MainActivity.DEFAULT_DICE_PACK

                    viewModel.presetSetDicesToDiceRepository(it, currentPack)
//                    findNavController().popBackStack()
                    pager.setCurrentItem(2, false) //bad
                },
                viewModel::deletePreset
            )
        }
        viewModel.presetsList.observe(viewLifecycleOwner) {
            adapter.listOfPresets = it
        }

        binding.presetRecycle.adapter = adapter
        binding.presetRecycle.layoutManager = LinearLayoutManager(requireContext())

        var names: List<String> = emptyList()
        viewModel.presetNames.observe(viewLifecycleOwner) {
            names = it
        }

        binding.addPresetButton.setOnClickListener {
            setPresetDialog(requireContext(), names)
        }
        return binding.root
    }

    private fun setPresetDialog(context: Context, listOfExistPresets: List<String>) {
        val inflater = LayoutInflater.from(context)
        val view = DialogPresetChangeNameBinding.inflate(inflater)
        view.dialogEditText.setHint(R.string.set_hint_preset_name)
        val dialog = AlertDialog.Builder(context)
            .setCancelable(false)
            .setMessage(context.getString(R.string.change_preset_name))
            .setPositiveButton(R.string.save, null)
            .setNegativeButton(R.string.dismiss, null)
            .setView(view.root)
            .create()
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val name = view.dialogEditText.text.toString().trim()
                if (name.isEmpty()) {
                    view.dialogEditText.error = context.getString(R.string.such_name_exist)
                    return@setOnClickListener
                }

                println(listOfExistPresets.forEach { println(it) })

                if (!listOfExistPresets.contains(name)) {
                    viewModel.insertPreset(name, DiceRepository.getDices())
                } else {
                    view.dialogEditText.error = context.getString(R.string.such_name_exist)
                    return@setOnClickListener
                }
                dialog.dismiss()
            }

        }

        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.show()
    }

}