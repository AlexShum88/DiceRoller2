package com.example.diceroller2.pack

import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller2.databinding.PackLineBinding
import com.example.diceroller2.model.Dice.Companion.IMAGE_RES
import com.example.diceroller2.model.DiceGrains


class PackAdapter(
    val fragment: PackFragment,
    val changeCurrentPack: (String)->Unit
) : RecyclerView.Adapter<PackAdapter.PackViewHolder>(), View.OnClickListener {

    inner class PackViewHolder(val binding: PackLineBinding) : RecyclerView.ViewHolder(binding.root)

    var packs = emptyList<String>()


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
        deletePreviousItemViews(holder)
        holder.binding.packFlow.referencedIds = createImageAssets(holder, pack)
    }

    private fun createImageView(diceImage: ImageView, patchToPicture:String){
        with(diceImage) {
            tag = TAG_DICE_IMAGE
            val drawableStream = fragment.requireContext().assets.open(patchToPicture)
            val drawable = VectorDrawable.createFromStream(drawableStream, null)
            diceImage.setImageDrawable(drawable)
            id = View.generateViewId()
        }
    }

    private fun createImageAssets(holder: PackViewHolder, pack: String): IntArray =

        DiceGrains.GRAINS.map {
            val diceImage = ImageView(holder.itemView.context)
            createImageView(diceImage, "$pack/$it/$it${IMAGE_RES}")
            holder.binding.packElementLayout.addView(diceImage)
            diceImage.id
        }.toIntArray()


    private fun deletePreviousItemViews(holder: PackViewHolder){
        holder.binding.packElementLayout.allViews
            .filter { it.tag == TAG_DICE_IMAGE }
            .toList()
            .forEach {
                holder.binding.packElementLayout.removeView(it)
            }
    }

    override fun getItemCount(): Int = packs.size

    override fun onClick(v: View) {
        val pack = v.tag as String
        changeCurrentPack(pack)
    }

    companion object {
        const val TAG_DICE_IMAGE = "diceImage"
    }
}