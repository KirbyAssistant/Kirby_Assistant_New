package ren.imyan.kirby.ui.mainpage

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.databinding.ActivityMainBinding
import ren.imyan.kirby.util.HideableBehavior
import ren.imyan.base.util.QMUIDisplayHelper
import ren.imyan.kirby.ui.setting.SettingActivity

class MainActivity : BaseUIActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        fun actionStart(context: Context) {
            startActivity<MainActivity>(context)
        }
    }

    override fun initViewModel(): MainViewModel =
        ViewModelProvider(this)[MainViewModel::class.java]

    override fun initBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


    override fun initToolbar(): Pair<Toolbar, *> =
        Pair(binding.layoutToolbar.toolbar, R.string.app_name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragmentPager()
        initBottomBar()
        binding.layoutToolbar.toolbar.elevation = 0f
        setSupportActionBar(binding.layoutToolbar.toolbar)
    }

    private fun initFragmentPager() {
        binding.fragmentViewpager.apply {
            adapter = ViewPagerAdapter(
                supportFragmentManager,
                lifecycle,
                viewModel.fragmentList
            )
            isUserInputEnabled = false
            offscreenPageLimit = 3
        }
    }

    private fun initBottomBar() {
        binding.navigation.setOnNavigationItemSelectedListener {
            binding.fragmentViewpager.currentItem = when (it.itemId) {
                R.id.res -> {
                    binding.layoutToolbar.toolbar.elevation = 0f
                    0
                }
                R.id.video ->
                {
                    binding.layoutToolbar.toolbar.elevation = 4f
                    1
                }
                else -> 0
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme -> getThemeManager().showSwitchDialog(::reloadMain)
            R.id.setting -> startActivity<SettingActivity>(this)
        }
        return true
    }

    /**
     * 用于重置 MainActivity
     */
    private fun reloadMain() {
        val reloadMain = Intent(this, MainActivity::class.java)
        reloadMain.putExtra("theme", true)
        startActivity(reloadMain)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}