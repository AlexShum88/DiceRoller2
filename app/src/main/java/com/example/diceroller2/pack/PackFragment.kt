package com.example.diceroller2.pack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.diceroller2.MainActivity
import com.example.diceroller2.databinding.FragmentPackListBinding
import com.example.diceroller2.model.DiceRepository

class PackFragment(val pager: ViewPager2) : Fragment() {


    val viewModel: PackViewModel by viewModels()
    lateinit var preferences: SharedPreferences
    lateinit var currentPack: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = this.requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
        currentPack =
            preferences.getString(MainActivity.CURRENT_DICE_PACK, MainActivity.DEFAULT_DICE_PACK)
                ?: MainActivity.DEFAULT_DICE_PACK
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPackListBinding.inflate(inflater, container, false)
        val adapter by lazy {
            PackAdapter(this)
            {
                preferences.edit()
                    .putString(MainActivity.CURRENT_DICE_PACK, it)
                    .apply()
                DiceRepository.changeDicePack(it)
                pager.setCurrentItem(2, false)

//                requireActivity().onBackPressed()
            }
        }
        adapter.packs = viewModel.packsName
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }


}