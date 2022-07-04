package com.example.diceroller2.chooseColor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.diceroller2.R
import com.example.diceroller2.TabAdapter
import com.example.diceroller2.databinding.FragmentChooseColorBinding
import com.example.diceroller2.factory

class ChooseColorFragment(private val pager: ViewPager2) :
    Fragment(R.layout.fragment_choose_color) {

    private lateinit var binding: FragmentChooseColorBinding
    private val viewModel: ChooseColorViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseColorBinding.inflate(inflater, container, false)
        val adapter by lazy {
            AdapterColor {
                viewModel.chooseColor(it)
                pager.setCurrentItem(TabAdapter.POSITION_OF_DICE_POOL, true)
            }
        }
        viewModel.colors.observe(viewLifecycleOwner) {
            adapter.dColors = it
        }
        binding.colorRecycler.adapter = adapter
        binding.colorRecycler.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onDestroy() {

        super.onDestroy()
        viewModel.onDestroy()
    }


    private fun setLayoutManager() {
//dynamic grid layout. dont create layout while dont roll dice (((
        binding.colorRecycler.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.colorRecycler.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.colorRecycler.width
                val itemWidth = resources.getDimensionPixelSize(R.dimen.dice_size)
                val columns = width / itemWidth
                binding.colorRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
            }
        })
    }

}