package ren.imyan.kirby.ui.cheatcode

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.data.model.CheatCode
import ren.imyan.kirby.data.model.Game
import ren.imyan.kirby.databinding.FragmentResBinding
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.game.GameListAdapter

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 17:13
 * @website https://imyan.ren
 */
class CheatCodeFragment : BaseFragment<ViewpagerResBinding, CheatCodeViewModel>() {

    private val parentViewModel: CheatCodeViewModel by activityViewModels()

    override fun initViewModel(): CheatCodeViewModel = parentViewModel

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): ViewpagerResBinding =
        ViewpagerResBinding.inflate(inflater, container, false)

    override fun initView() {
    }

    override fun loadDate() {
        viewModel.cheatCodeData.observe(this) {
            showListData(it)
        }
        viewModel.cheatCodeDataState.observe(this) {
            showErrorMessage(
                it, viewModel.game.tag
            )
        }
    }

    private fun showListData(data: List<CheatCode>) {
        binding.showDataList.layoutManager = GridLayoutManager(activity, 1)
        binding.showDataList.adapter = CheatCodeListAdapter(data)
        binding.loadBar.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun showErrorMessage(error: String, type: String) {
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