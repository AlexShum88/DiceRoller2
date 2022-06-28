package com.example.diceroller2.dicepool

import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.databinding.DiceBinding
import com.example.diceroller2.model.Dice

class DiceAdapter(
    private val fragment: DicePoolFragment,
    private val adapterActions: AdapterActions,
    private val dicePopUpAction: DicePopUpAction,
) : RecyclerView.Adapter<DiceAdapter.ViewHolder>(), View.OnClickListener, View.OnLongClickListener {


    class ViewHolder(val binding: DiceBinding) : RecyclerView.ViewHolder(binding.root)

    var dices = emptyList<Dice>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }

    var divider: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DiceBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dice = dices[position]
        with(holder.binding) {
            root.tag = dice

//            diceImage.setImageResource(dice.image)
            val asd = fragment.requireContext().assets.open(dice.image)
            val d = VectorDrawable.createFromStream(asd, null)

            diceImage.setImageDrawable(d)
            diceImage.setBackgroundColor(fragment.requireContext().getColor(dice.color))
            if (divider >= 0) {
                if (position <= divider) {
                    root.alpha = MAX_ALPHA
                } else {
                    root.alpha = MIN_ALPHA
                }
            }
//            DrawableCompat.setTint(diceImage.drawable, ContextCompat.getColor(fragment.requireContext(), dice.color))
            diceValue.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = dices.size


    override fun onClick(diceView: View) {
        val dice = diceView.tag as Dice
        val index = dices.indexOfFirst { it === dice }
        if (index < 0) return
        divider = index
        adapterActions.onClickRoll(dice)
        divider = if (adapterActions.setChangeAlphaRegime()) dices.size
        else index
        println(dice.value)
    }

    override fun onLongClick(diceView: View): Boolean {
        PopUp(diceView, dicePopUpAction).createDicePop()
        return true
    }

    companion object{
        const val MAX_ALPHA = 1F
        const val MIN_ALPHA = 0.6F
    }
}