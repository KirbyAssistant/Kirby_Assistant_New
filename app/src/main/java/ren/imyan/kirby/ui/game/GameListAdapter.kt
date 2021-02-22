package ren.imyan.kirby.ui.game

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import ren.imyan.base.ActivityCollector
import ren.imyan.base.BaseAdapter
import ren.imyan.kirby.R
import ren.imyan.kirby.RecyclerAnimation
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.data.model.moshi.Game
import ren.imyan.kirby.databinding.ItemResBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 12:16
 * @website https://imyan.ren
 */
class GameListAdapter(data: List<Game>) :
    BaseAdapter<Game, ItemResBinding>(data.toMutableList(), R.layout.item_res) {

    companion object {
        val JP_VERSION =
            currActivity.resources.getString(R.string.dia_jp)
        val US_VERSION =
            currActivity.resources.getString(R.string.dia_us)
        val CN_VERSION =
            currActivity.resources.getString(R.string.dia_cn)
    }

    override fun bindItem(itemBinding: ItemResBinding, itemData: Game) {
        itemBinding.root.startAnimation(RecyclerAnimation.itemAnimation)
        itemBinding.image.animation = RecyclerAnimation.alphaAnimation
        itemBinding.blurImage.animation = RecyclerAnimation.alphaAnimation
        itemBinding.name.animation = RecyclerAnimation.alphaAnimation

        itemBinding.name.text = itemData.title
    }

    override fun bindAfterExecute(itemBinding: ItemResBinding, itemData: Game) {
        Glide.with(currActivity)
            .load(itemData.image)
            .into(itemBinding.image)
    }
}