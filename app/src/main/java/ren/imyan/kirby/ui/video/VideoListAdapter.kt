package ren.imyan.kirby.ui.video

import com.bumptech.glide.Glide
import ren.imyan.base.BaseAdapter
import ren.imyan.kirby.R
import ren.imyan.kirby.RecyclerAnimation
import ren.imyan.kirby.data.model.moshi.Video
import ren.imyan.kirby.databinding.ItemVideoBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 19:28
 * @website https://imyan.ren
 */
class VideoListAdapter(data: List<Video>) :
    BaseAdapter<Video, ItemVideoBinding>(data.toMutableList(), R.layout.item_video) {
    override fun bindItem(itemBinding: ItemVideoBinding, itemData: Video) {
        itemBinding.root.startAnimation(RecyclerAnimation.itemAnimation)
        itemBinding.videoText.animation = RecyclerAnimation.alphaAnimation
        itemBinding.videoImage.animation = RecyclerAnimation.alphaAnimation

        itemBinding.videoText.text = itemData.title
    }

    override fun bindAfterExecute(itemBinding: ItemVideoBinding, itemData: Video) {
        Glide.with(context).load(itemData.imageUrl).into(itemBinding.videoImage)
    }
}