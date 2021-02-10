package ren.imyan.kirby.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ren.imyan.base.ActivityCollector
import ren.imyan.kirby.R
import ren.imyan.kirby.data.model.Game
import ren.imyan.kirby.databinding.ItemResBinding
import ren.imyan.kirby.ui.ResViewHolder

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-10 19:38
 * @website https://imyan.ren
 */
class GameListAdapter(private val data: List<Game>) :
    RecyclerView.Adapter<ResViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResViewHolder {
        val binding = ItemResBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ResViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ResViewHolder, position: Int) {
        val ele = data[position]

        val animation = AnimationUtils.loadAnimation(
            ActivityCollector.currActivity(),
            R.anim.anim_recycler_item_show
        )
        val alphaAnimation = AlphaAnimation(0.1f, 1.0f)
        alphaAnimation.duration = 500

        holder.itemRes.startAnimation(animation)
        holder.image.animation = alphaAnimation
        holder.blurImage.animation = alphaAnimation
        holder.name.animation = alphaAnimation

        holder.name.text = ele.title
        Glide.with(ActivityCollector.currActivity())
            .load(ele.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int = data.size

}