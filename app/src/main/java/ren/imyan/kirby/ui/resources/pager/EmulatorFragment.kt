package ren.imyan.kirby.ui.resources.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.resources.ResFragment
import ren.imyan.kirby.ui.resources.ResViewModel

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 0:16
 * @website https://imyan.ren
 */
class EmulatorFragment:BaseFragment<ViewpagerResBinding,ResViewModel>() {
    private val resFragment: ResFragment by lazy {
        requireParentFragment() as ResFragment
    }

    override fun initViewModel(): ResViewModel =
        ViewModelProvider(requireParentFragment())[ResViewModel::class.java]

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): ViewpagerResBinding =
        ViewpagerResBinding.inflate(inflater)

    override fun initView() {

    }

    override fun loadDate() {
        viewModel.emulatorListData.observe(this) {
            resFragment.showListData(binding, it)
        }
        viewModel.emulatorDataState.observe(this) {
            resFragment.showErrorMessage(binding, it, "console")
        }
    }
}