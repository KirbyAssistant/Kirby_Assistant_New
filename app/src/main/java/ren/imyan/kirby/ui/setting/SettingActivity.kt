package ren.imyan.kirby.ui.setting

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseUIActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.databinding.ActivitySettingBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 19:20
 * @website https://imyan.ren
 */
class SettingActivity : BaseUIActivity<ActivitySettingBinding, SettingViewModel>() {
    override fun initViewModel(): SettingViewModel =
        ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initBinding(): ActivitySettingBinding =
        ActivitySettingBinding.inflate(layoutInflater)


    override fun initToolbar(): Pair<Toolbar, *> =
        Pair(binding.layoutToolbar.toolbar, R.string.setting_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, SettingFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}