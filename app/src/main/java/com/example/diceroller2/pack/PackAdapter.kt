package com.example.diceroller2.pack

import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.databinding.PackLineBinding
import com.example.diceroller2.model.DiceRepository

interface PackOnClickActions{
    fun changeCurrentPack(packName: String)
}

class PackAdapter(
    val fragment: PackFragment,
    val packOnClickActions: PackOnClickActions
) : RecyclerView.Adapter<PackAdapter.PackViewHolder>(), View.OnClickListener {

    inner class PackViewHolder(val binding: PackLineBinding) : RecyclerView.ViewHolder(binding.root)

    var packs = emptyList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PackLineBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return PackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackViewHolder, position: Int) {
        val pack = packs[position]
        holder.binding.packElementName.text = pack
        holder.binding.root.tag = pack
        holder.binding.packElementLayout.allViews
            .filter { it.tag == "diceImage" }
            .toList()
            .forEach {
                holder.binding.packElementLayout.removeView(it)
            }

        val imageAssets = DiceRepository.GRAINS.map {
            val diceImage = ImageView(holder.itemView.context)
            with(diceImage) {
                tag = "diceImage"
                val asd = fragment.requireContext().assets.open("$pack/$it/$it.png")
                val d = VectorDrawable.createFromStream(asd, null)
                diceImage.setImageDrawable(d)
//            diceImage.setBackgroundColor(diceImage.context.getColor(dice.color))
                id = View.generateViewId()
            }
            holder.binding.packElementLayout.addView(diceImage)
            diceImage.id
        }

        holder.binding.packFlow.referencedIds = imageAssets.toIntArray()
    }

    override fun getItemCount(): Int = packs.size

    override fun onClick(v: View) {
        val pack = v.tag as String
        packOnClickActions.changeCurrentPack(pack)
    }
}