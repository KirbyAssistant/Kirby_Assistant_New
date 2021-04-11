package ren.imyan.kirby.ui.resources.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.resources.ResFragment
import ren.imyan.kirby.ui.resources.ResListAdapter
import ren.imyan.kirby.ui.resources.ResViewModel
import ren.imyan.kirby.util.showErrorMessage
import ren.imyan.kirby.util.showListData

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 0:16
 * @website https://imyan.ren
 */
class EmulatorFragment : BaseFragment<ViewpagerResBinding, ResViewModel>() {

    override fun initViewModel(): ResViewModel =
        ViewModelProvider(requireParentFragment())[ResViewModel::class.java]

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): ViewpagerResBinding =
        ViewpagerResBinding.inflate(inflater)

    override fun initView() {

    }

    override fun loadDate() {
        viewModel.emulatorListData.data.observe(this) {
            binding.showListData(ResListAdapter(it))
        }
        viewModel.emulatorListData.state.observe(this) {
            binding.showErrorMessage(it, "console", viewModel)
        }
    }
}