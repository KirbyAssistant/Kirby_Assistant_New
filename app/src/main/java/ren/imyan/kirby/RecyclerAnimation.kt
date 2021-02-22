package ren.imyan.kirby

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import ren.imyan.base.ActivityCollector
import ren.imyan.kirby.core.currActivity

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 12:21
 * @website https://imyan.ren
 */
object RecyclerAnimation {
    val itemAnimation: Animation
        get() = AnimationUtils.loadAnimation(
            currActivity,
            R.anim.anim_recycler_item_show
        )

    val alphaAnimation: AlphaAnimation
        get() {
            val anim = AlphaAnimation(0.1f, 1.0f)
            anim.duration = 500
            return anim
        }
}