package ren.imyan.kirby.ui.mainpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.databinding.ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolBar()
        initFragmentPager()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setToolBarTitle(R.string.app_name)
    }

    private fun initFragmentPager() {
        binding.mainFragmentViewpager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            viewModel.fragmentList
        )
        binding.mainFragmentViewpager.isUserInputEnabled = false
        binding.mainFragmentViewpager.offscreenPageLimit = 3
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme -> getThemeManager().showSwitchDialog(::reloadMain)
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