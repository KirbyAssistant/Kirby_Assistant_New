package ren.imyan.kirby.ui.resources

import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import ren.imyan.base.BaseAdapter
import ren.imyan.kirby.R
import ren.imyan.kirby.RecyclerAnimation
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.ItemResBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-21 16:01
 * @website https://imyan.ren
 */
class ResListAdapter(data:List<ResItem>):BaseAdapter<ResItem, ItemResBinding>(data.toMutableList(), R.layout.item_res) {

    override fun bindItem(itemBinding: ItemResBinding, itemData: ResItem) {
        itemBinding.root.startAnimation(RecyclerAnimation.itemAnimation)
        itemBinding.image.animation = RecyclerAnimation.alphaAnimation
        itemBinding.blurImage.animation = RecyclerAnimation.alphaAnimation
        itemBinding.name.animation = RecyclerAnimation.alphaAnimation

        itemBinding.res = itemData
        itemBinding.name.text = itemData.title
    }

    override fun bindAfterExecute(itemBinding: ItemResBinding, itemData: ResItem) {
        Glide.with(context)
            .load(itemData.imageUrl)
//            .apply(Kirby.getGlideRequestOptions())
            .into(itemBinding.image)
    }
}