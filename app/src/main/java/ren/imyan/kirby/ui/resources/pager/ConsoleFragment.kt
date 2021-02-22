package ren.imyan.kirby.ui.resources.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.resources.ResFragment
import ren.imyan.kirby.ui.resources.ResListAdapter
import ren.imyan.kirby.ui.resources.ResViewModel
import ren.imyan.kirby.util.showListData

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 0:00
 * @website https://imyan.ren
 */
class ConsoleFragment : BaseFragment<ViewpagerResBinding, ResViewModel>() {

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
        viewModel.consoleListData.observe(this) {
//            resFragment.showListData(binding, it)
            binding.showListData(ResListAdapter(it))

        }
        viewModel.consoleDataState.observe(this) {
            resFragment.showErrorMessage(binding, it, "console")
        }
    }
}