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
import com.example.diceroller2.R
import com.example.diceroller2.TabAdapter
import com.example.diceroller2.databinding.FragmentPackListBinding
import com.example.diceroller2.model.DiceRepository

class PackFragment() : Fragment() {

    lateinit var pager: ViewPager2
    val viewModel: PackViewModel by viewModels()
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = this.requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
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
                pager.setCurrentItem(TabAdapter.POSITION_OF_DICE_POOL, false)
            }
        }
        adapter.packs = viewModel.packsName
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        pager = requireActivity().findViewById(R.id.pager)
        return binding.root
    }


}