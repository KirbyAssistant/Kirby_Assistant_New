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
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.databinding.ActivityMainBinding
import ren.imyan.kirby.util.HideableBehavior

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


    override fun initToolbar(): Pair<Toolbar, *> = Pair(binding.toolbarLayout.toolbar,R.string.app_name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragmentPager()
        requestNavigationHidden()
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

    @Suppress("UNCHECKED_CAST")
    internal fun requestNavigationHidden(hide: Boolean = true) {
        val topView = binding.appBar
        val bottomView = binding.mainBottomBar

        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
            !binding.mainBottomBar.isAttachedToWindow
        ) {
            binding.mainBottomBar.viewTreeObserver.addOnWindowAttachListener(object :
                ViewTreeObserver.OnWindowAttachListener {

                init {
                    val listener =
                        binding.mainBottomBar.tag as? ViewTreeObserver.OnWindowAttachListener
                    if (listener != null) {
                        binding.mainBottomBar.viewTreeObserver.removeOnWindowAttachListener(listener)
                    }
                    binding.mainBottomBar.tag = this
                }

                override fun onWindowAttached() {
                    requestNavigationHidden(hide)
                }

                override fun onWindowDetached() {
                }
            })
            return
        }

        val topParams = topView.layoutParams as? CoordinatorLayout.LayoutParams
        val bottomParams = bottomView.layoutParams as? CoordinatorLayout.LayoutParams

        val topBehavior = topParams?.behavior as? HideableBehavior<View>
        val bottomBehavior = bottomParams?.behavior as? HideableBehavior<View>

        topBehavior?.setHidden(topView, hide = false, lockState = false)
        bottomBehavior?.setHidden(bottomView, hide, hide)
    }
}