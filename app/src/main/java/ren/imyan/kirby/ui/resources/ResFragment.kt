package ren.imyan.kirby.ui.resources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.databinding.FragmentResBinding
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.ktx.toast

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 16:37
 * @website https://imyan.ren
 */
class ResFragment : BaseFragment<FragmentResBinding, ResViewModel>() {

    override fun initViewModel(): ResViewModel = ViewModelProvider(this)[ResViewModel::class.java]

    override fun initBinding(
        view: LayoutInflater,
        container: ViewGroup?
    ): FragmentResBinding = FragmentResBinding.inflate(view, container, false)


    override fun initView() {
        val inflater = LayoutInflater.from(activity)

        val consolePagerBinding = ViewpagerResBinding.inflate(inflater)
        val emulatorPagerBinding = ViewpagerResBinding.inflate(inflater)
        val cheatCodePagerBinding = ViewpagerResBinding.inflate(inflater)

        binding.mainResTablayout.tabMode = TabLayout.MODE_FIXED

        binding.mainResViewpager.adapter = ResPagerAdapter(
            viewModel.tabTitles, listOf(
                consolePagerBinding.root,
                emulatorPagerBinding.root,
                cheatCodePagerBinding.root
            )
        )
        binding.mainResTablayout.setupWithViewPager(binding.mainResViewpager)
    }

    override fun loadDate() {

    }
}