package ren.imyan.kirby.ui

import android.os.Bundle
import ren.imyan.base.BaseActivity

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 16:38
 * @website https://imyan.ren
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAndStart()
    }

    private fun initAndStart() {
        MainActivity.actionStart(this)
    }
}