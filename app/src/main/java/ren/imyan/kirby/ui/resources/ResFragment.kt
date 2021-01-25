package ren.imyan.kirby.ui.resources

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.FragmentResBinding
import ren.imyan.kirby.databinding.ViewpagerResBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 16:37
 * @website https://imyan.ren
 */
class ResFragment : BaseFragment<FragmentResBinding, ResViewModel>() {

    private lateinit var consolePagerBinding: ViewpagerResBinding
    private lateinit var emulatorPagerBinding: ViewpagerResBinding
    private lateinit var cheatCodePagerBinding: ViewpagerResBinding

    override fun initViewModel(): ResViewModel = ViewModelProvider(this)[ResViewModel::class.java]

    override fun initBinding(
        view: LayoutInflater,
        container: ViewGroup?
    ): FragmentResBinding = FragmentResBinding.inflate(view, container, false)


    override fun initView() {
        val inflater = LayoutInflater.from(activity)

        consolePagerBinding = ViewpagerResBinding.inflate(inflater)
        emulatorPagerBinding = ViewpagerResBinding.inflate(inflater)
        cheatCodePagerBinding = ViewpagerResBinding.inflate(inflater)

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
        // 获取到模拟器数据
        viewModel.consoleListData.observe(this) {
            showListData(consolePagerBinding, it)
        }
        // 获取模拟器数据失败
        viewModel.consoleDataState.observe(this) {
            showErrorMessage(consolePagerBinding, it, "console")
        }
        viewModel.emulatorListData.observe(this) {
            showListData(emulatorPagerBinding, it)
        }
        viewModel.emulatorDataState.observe(this) {
            showErrorMessage(emulatorPagerBinding, it, "emulator")
        }
    }

    private fun showListData(binding: ViewpagerResBinding, data: List<ResItem>) {
        binding.showDataList.layoutManager = GridLayoutManager(activity, 1)
        binding.showDataList.adapter = ResListAdapter(data)
        binding.loadBar.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun showErrorMessage(binding: ViewpagerResBinding, error: String, type: String) {
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