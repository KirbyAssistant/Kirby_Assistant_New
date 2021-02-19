package ren.imyan.kirby.ui

import android.os.Bundle
import ren.imyan.base.BaseActivity
import ren.imyan.kirby.ui.mainpage.MainActivity
import ren.imyan.kirby.util.SP

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