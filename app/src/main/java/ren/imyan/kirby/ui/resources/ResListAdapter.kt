package ren.imyan.kirby.ui.resources

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import cn.ednureblaze.glidecache.GlideCache
import com.bumptech.glide.Glide
import ren.imyan.base.ActivityCollector
import ren.imyan.kirby.R
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.ItemResBinding
import ren.imyan.kirby.ui.ResViewHolder
import ren.imyan.kirby.ui.game.GameListActivity

class ResListAdapter(private val resList: List<ResItem>) :
    RecyclerView.Adapter<ResViewHolder>() {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val itemResBinding = ItemResBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ResViewHolder(itemResBinding)
    }

    override fun getItemCount(): Int = resList.size

    override fun onBindViewHolder(holder: ResViewHolder, position: Int) {
        val res = resList[position]
        val animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_recycler_item_show)
        val alphaAnimation = AlphaAnimation(0.1f, 1.0f)
        alphaAnimation.duration = 500

        holder.itemRes.startAnimation(animation)
        holder.image.animation = alphaAnimation
        holder.name.animation = alphaAnimation
        holder.blurImage.animation = alphaAnimation

        holder.name.text = res.title
        Glide.with(mContext!!)
            .load(res.imageUrl)
//            .apply(Kirby.getGlideRequestOptions())
            .into(holder.image)

        if (res.type != "console") {
            GlideCache.setBlurImageViaGlideCache(ActivityCollector.currActivity(), holder.blurImage, res.imageUrl, "8")
        }

        holder.linearLayout.setOnClickListener {
            when (res.type) {
            "console" -> mContext?.let { GameListActivity.actionStart(it, res) }
//            "cheatcode" -> CheatCodeActivity.actionStart(mContext!!, res.tag)
        }
        }
    }
}