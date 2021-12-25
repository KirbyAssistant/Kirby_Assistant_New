package ren.imyan.kirby.ui

import androidx.activity.viewModels
import androidx.core.view.size
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBarOnly
import ren.imyan.kirby.R
import ren.imyan.kirby.common.ktx.binding
import ren.imyan.kirby.common.utils.toast
import ren.imyan.kirby.core.BaseActivity
import ren.imyan.kirby.databinding.ActivityMainBinding
import ren.imyan.kirby.ui.console.ConsoleSelectFragment
import ren.imyan.kirby.ui.emu.EmulatorFragment

class MainActivity : BaseActivity() {
    private val binding by binding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MainViewModel>()

    private val fragmentClazzList by lazy {
        listOf(
            ConsoleSelectFragment::class.java,
            EmulatorFragment::class.java,
            ConsoleSelectFragment::class.java,
            ConsoleSelectFragment::class.java
        )
    }

    override fun initView() {
        super.initView()
        statusBar {
            transparent()
            light = true
        }
        navigationBar {
            transparent()
        }
        binding.apply {
            fragmentViewpager.apply {
                offscreenPageLimit = fragmentViewpager.size
                adapter = MainFragmentAdapter(this@MainActivity)
                isUserInputEnabled = false
            }
            navigation.updatePadding(bottom = navigationBarHeight)
            navigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.game -> {
                        fragmentViewpager.currentItem = 0
                    }
                    R.id.emu -> {
                        fragmentViewpager.currentItem = 1
                    }
                    R.id.video -> {
                        toast("暂时不可用哦")
//                        fragmentViewpager.currentItem = 2
                       return@setOnItemSelectedListener false
                    }
                    R.id.cheat_code -> {
                        toast("暂时不可用哦")
//                        fragmentViewpager.currentItem = 3
                        return@setOnItemSelectedListener false
                    }
                    else -> {
                        fragmentViewpager.currentItem = 0
                    }
                }
                true
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel
    }

    inner class MainFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = fragmentClazzList.size

        override fun createFragment(position: Int): Fragment =
            fragmentClazzList[position].newInstance()

    }
}