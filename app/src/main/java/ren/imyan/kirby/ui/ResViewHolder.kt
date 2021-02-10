package ren.imyan.kirby.ui

import androidx.recyclerview.widget.RecyclerView
import ren.imyan.kirby.databinding.ItemResBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-10 19:46
 * @website https://imyan.ren
 */
class ResViewHolder(itemResBinding: ItemResBinding) : RecyclerView.ViewHolder(itemResBinding.root) {
    val itemRes = itemResBinding.root
    val linearLayout = itemResBinding.LinearLayout
    val name = itemResBinding.name
    val image = itemResBinding.image
    val blurImage = itemResBinding.blurImage
}