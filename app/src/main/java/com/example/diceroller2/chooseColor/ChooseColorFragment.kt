package com.example.diceroller2.chooseColor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diceroller2.R
import com.example.diceroller2.databinding.FragmentChooseColorBinding
import com.example.diceroller2.factory

import com.example.diceroller2.model.DColor

class ChooseColorFragment : Fragment(R.layout.fragment_choose_color) {


    lateinit var binding: FragmentChooseColorBinding
    private val viewModel: ChooseColorViewModel by viewModels{factory()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseColorBinding.inflate(inflater, container, false)

        binding.colorDoneButton.setOnClickListener { findNavController().navigateUp() }

        val adapter = AdapterColor(
            object : ColorActionListener{
                override fun changeFocus(dColor: DColor) {
                    viewModel.chooseColor(dColor)
                }
            }
        )

        viewModel.colors.observe(viewLifecycleOwner){
            adapter.dColors = it
        }

        binding.colorRecycler.adapter = adapter
        setLayoutManager()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }


    fun setLayoutManager() {

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

    companion object {
        fun newInstance() = ChooseColorFragment()
    }
}