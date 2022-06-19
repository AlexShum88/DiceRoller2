package com.example.diceroller2.statistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.R
import com.example.diceroller2.databinding.StatisticLineBinding
import com.example.diceroller2.model.Dice
import com.example.diceroller2.model.StatisticObject

class StatisticAdapter : RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder>() {

    class StatisticViewHolder(val binding: StatisticLineBinding) :
        RecyclerView.ViewHolder(binding.root)

    var list = emptyList<StatisticObject>()
        set(value) {
            field = value.reversed()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StatisticLineBinding.inflate(inflater)
        return StatisticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatisticViewHolder, position: Int) {
        val item = list[position]
        holder.binding.dateTextView.text = item.date
        val statistic = createStatText(item.dices)

        statistic.forEach {

            val grain = TextView(holder.itemView.context)
            grain.textSize = 32f
            grain.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            grain.text = holder.itemView.context.getString(R.string.statistic_grain_text, item.dices.size, it.grain)
//            grain.id  = View.generateViewId()
            holder.binding.root.addView(grain)

            val colorsRow = it.colorsAndResults
            colorsRow.forEach { a->
                val colorRow = TextView(holder.itemView.context)
                colorRow.textSize = 24f
                colorRow.setTextColor(ContextCompat.getColor(holder.itemView.context, a.key))
                colorRow.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                colorRow.text = a.value
//                colorRow.id  = View.generateViewId()
                holder.binding.root.addView(colorRow)
            }
        }

    }


    override fun getItemCount(): Int = list.size

    fun sortByColor(dices: List<Dice>): Map<Int, List<Dice>> {
        val resultMap = mutableMapOf<Int, List<Dice>>()
        dices.map { it.color }.map { a ->
            resultMap[a] = dices.filter {
                it.color == a
            }.toList()
        }
        return resultMap
    }

    fun sortByGrain(dices: List<Dice>): Map<Int, List<Dice>> {
        val resultMap = mutableMapOf<Int, List<Dice>>()
        dices.map { it.grain }.map { a ->
            resultMap[a] = dices.filter {
                it.grain == a
            }.toList()
        }
        return resultMap
    }

    fun getDiceValues(dices: List<Dice>): List<Int> {
        return dices.map { it.value }
    }

    fun result(dices: List<Dice>): String {
        val e = StringBuilder()
        val res =  dices.map {
            e.append("${it.value} ")
            it.value
        }.reduce { a, b ->
            a + b }
        e.append("($res)")
        return e.toString()
    }


    fun createStatText(dices: List<Dice>): List<StatTextObj>{
        val statTextList = mutableListOf<StatTextObj>()
        val grainSort = sortByGrain(dices)
        grainSort.forEach {
            val statText = StatTextObj()
            statText.grain = it.key.toString()
            statText.colorsAndResults = sortByColor(it.value).mapValues { a->result(a.value)}
            statTextList.add(statText)
        }
        return statTextList
    }

    class StatTextObj(
        var grain: String = "",
        var colorsAndResults: Map<Int, String> = emptyMap(),
    )
}