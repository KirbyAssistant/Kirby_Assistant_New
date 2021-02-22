package ren.imyan.kirby.ui.resources

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.FragmentResBinding
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.cheatcode.CheatCodeActivity
import ren.imyan.kirby.ui.game.GameListActivity
import ren.imyan.kirby.ui.resources.pager.CheatCodeGameFragment
import ren.imyan.kirby.ui.resources.pager.ConsoleFragment
import ren.imyan.kirby.ui.resources.pager.EmulatorFragment
import ren.imyan.ktx.toast

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 16:37
 * @website https://imyan.ren
 */
class ResFragment : BaseFragment<FragmentResBinding, ResViewModel>() {

    override fun initViewModel(): ResViewModel = ViewModelProvider(this)[ResViewModel::class.java]

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResBinding = FragmentResBinding.inflate(inflater, container, false)

    override fun initView() {
        binding.mainResTablayout.tabMode = TabLayout.MODE_FIXED

        binding.mainResViewpager.adapter = ResPagerAdapter(
            this,
            listOf(
                ConsoleFragment(),
                EmulatorFragment(),
                CheatCodeGameFragment()
            )
        )

        TabLayoutMediator(binding.mainResTablayout, binding.mainResViewpager) { tab, position ->
            tab.text = viewModel.tabTitles[position]
        }.attach()
    }

    override fun loadDate() {
    }

    @SuppressLint("SetTextI18n")
    fun showErrorMessage(binding: ViewpagerResBinding, error: String, type: String) {
        binding.loadBar.visibility = View.GONE
        binding.errMsg.visibility = View.VISIBLE
        binding.errMsg.text = "$error\n请点击重试或截图告知开发者"
        binding.root.setOnClickListener {
            binding.errMsg.visibility = View.GONE
            binding.loadBar.visibility = View.VISIBLE
            viewModel.getAgainData(type)
        }
    }
}