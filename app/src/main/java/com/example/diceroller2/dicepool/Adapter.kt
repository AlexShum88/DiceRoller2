package com.example.diceroller2.dicepool

import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.DiffUtilComparator
import com.example.diceroller2.databinding.DiceBinding
import com.example.diceroller2.model.Dice

typealias SwitchListener = (switch: Boolean)-> Unit
class DiceAdapter(
    private val fragment: DicePoolFragment,
    private val adapterActions: AdapterActions,
    private val dicePopUpAction: DicePopUpAction,
): RecyclerView.Adapter<DiceAdapter.ViewHolder>(), View.OnClickListener, View.OnLongClickListener {

    class ViewHolder(val binding: DiceBinding): RecyclerView.ViewHolder(binding.root)
    var dices = emptyList<Dice>()
        set(value) {
            val lav = listOf(value).map { it[0] }
            val callback = DiffUtilComparator(field, lav)
            val resualt = DiffUtil.calculateDiff(callback)
            field = value
            resualt.dispatchUpdatesTo(this)

        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DiceBinding.inflate(inflater, parent, false)
//        binding.diceImage.setOnClickListener(this)
        binding.root.setOnClickListener (this)
        binding.root.setOnLongClickListener(this)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dice = dices[position]
        with(holder.binding){
//            diceImage.tag = dice
            root.tag = dice

//            diceImage.setImageResource(dice.image)
            val asd = fragment.requireContext().assets.open("test.xml")
            val d = VectorDrawable.createFromStream(asd, null)

            diceImage.setImageDrawable(d)
//            diceImage.setColorFilter(R.color.teal_700)

//            DrawableCompat.setTint(diceImage.drawable, ContextCompat.getColor(fragment.requireContext(), dice.color))
            diceValue.textSize = 36f
            diceValue.text = dice.value.toString()

        }
    }

    override fun getItemCount(): Int = dices.size


    override fun onClick(diceView: View) {
        val dice = diceView.tag as Dice
        adapterActions.onClickRoll(dice)
        println(dice.value)
    }

    override fun onLongClick(diceView: View): Boolean {

//        Toast.makeText(fragment.requireContext(), "LongClick", Toast.LENGTH_SHORT).show()
        PopUp(diceView, dicePopUpAction).createDicePop()
        return true
    }

}