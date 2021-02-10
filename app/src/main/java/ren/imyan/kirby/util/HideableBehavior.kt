package ren.imyan.kirby.util

import android.view.View

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 16:50
 * @website https://imyan.ren
 */
interface HideableBehavior<V : View> {

    fun setHidden(view: V, hide: Boolean, lockState: Boolean = false)

}