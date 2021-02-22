package ren.imyan.kirby.ui.setting

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import ren.imyan.base.BaseUIActivity
import ren.imyan.kirby.databinding.ActivitySettingBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 19:20
 * @website https://imyan.ren
 */
class SettingActivity:BaseUIActivity<ActivitySettingBinding,SettingViewModel>() {
    override fun initViewModel(): SettingViewModel = TODO()

    override fun initBinding(): ActivitySettingBinding {
        TODO("Not yet implemented")
    }

    override fun initToolbar(): Pair<Toolbar, *> {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}