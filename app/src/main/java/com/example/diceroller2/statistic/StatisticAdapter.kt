package com.example.diceroller2.statistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
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
        val binding = StatisticLineBinding.inflate(inflater, parent, false)
        return StatisticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatisticViewHolder, position: Int) {
        val item = list[position]
        holder.binding.dateTextView.text = item.date
        val statistic = createStatText(item.dices)
        val context = holder.itemView.context

        holder.binding.layoutForDices.allViews
            .filter { it.tag == TAG_DICE_VIEW }
            .toList()
            .forEach {
                holder.binding.layoutForDices.removeView(it)
            }

        statistic.forEach {

            val grain = TextView(holder.itemView.context)
            grain.tag = TAG_DICE_VIEW
            grain.textSize =
                context.resources.getDimension(R.dimen.text_size_in_generated_view_big) / TEXT_SIZE_CORRECTION_DIVIDER
            grain.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

            grain.text = context.getString(R.string.statistic_grain_text, item.dices.size, it.grain)
            holder.binding.layoutForDices.addView(grain)

            val colorsRow = it.colorsAndResults
            colorsRow.forEach { a ->
                val colorRow = TextView(holder.itemView.context)
                colorRow.tag = TAG_DICE_VIEW
                colorRow.textSize =
                    context.resources.getDimension(R.dimen.text_size_in_generated_view) / TEXT_SIZE_CORRECTION_DIVIDER
                colorRow.setTextColor(ContextCompat.getColor(holder.itemView.context, a.key))
                colorRow.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                colorRow.text = a.value
                holder.binding.layoutForDices.addView(colorRow)
            }
        }

    }


    override fun getItemCount(): Int = list.size

    private fun sortByColor(dices: List<Dice>): Map<Int, List<Dice>> {
        val resultMap = mutableMapOf<Int, List<Dice>>()
        dices.map { it.color }.map { a ->
            resultMap[a] = dices.filter {
                it.color == a
            }.toList()
        }
        return resultMap
    }

    private fun sortByGrain(dices: List<Dice>): Map<Int, List<Dice>> {
        val resultMap = mutableMapOf<Int, List<Dice>>()
        dices.map { it.grain }.map { a ->
            resultMap[a] = dices.filter {
                it.grain == a
            }.toList()
        }
        return resultMap
    }


    private fun result(dices: List<Dice>): String {
        val e = StringBuilder()
        val res = dices.map {
            e.append("${it.value} ")
            it.value
        }.reduce { a, b ->
            a + b
        }
        e.append("($res)")
        return e.toString()
    }


    private fun createStatText(dices: List<Dice>): List<StatTextObj> {
        val statTextList = mutableListOf<StatTextObj>()
        val grainSort = sortByGrain(dices)
        grainSort.forEach {
            val statText = StatTextObj()
            statText.grain = it.key.toString()
            statText.colorsAndResults = sortByColor(it.value).mapValues { a -> result(a.value) }
            statTextList.add(statText)
        }
        return statTextList
    }

    private class StatTextObj(
        var grain: String = "",
        var colorsAndResults: Map<Int, String> = emptyMap(),
    )

    companion object {
        const val TAG_DICE_VIEW = "diceView"
        const val TEXT_SIZE_CORRECTION_DIVIDER = 4
    }
}