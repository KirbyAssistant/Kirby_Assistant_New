package ren.imyan.kirby.ui.console

import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight
import org.koin.core.qualifier.named
import ren.imyan.kirby.R
import ren.imyan.kirby.common.ktx.binding
import ren.imyan.kirby.common.ktx.get
import ren.imyan.kirby.common.ktx.observeState
import ren.imyan.kirby.core.BaseFragment
import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.databinding.FragmentOnlyListBinding
import ren.imyan.kirby.net.response.ConsoleResponse
import ren.imyan.kirby.ui.MainAction
import ren.imyan.kirby.ui.MainData
import ren.imyan.kirby.ui.MainViewModel
import ren.imyan.kirby.ui.game.GameAction
import ren.imyan.kirby.ui.game.GameActivity

class ConsoleSelectFragment : BaseFragment(R.layout.fragment_only_list) {
    private val binding by binding(FragmentOnlyListBinding::bind)
    private val viewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy {
        ConsoleAdapter().apply {
//            addHeaderView(get(named("statusBarPadding")))
            setOnItemClickListener { adapter, view, position ->
                Intent(requireContext(), GameActivity::class.java).apply {
                    putExtra("console", (adapter.data[position] as ConsoleResponse).tag)
                    startActivity(this)
                }
            }
        }
    }

    override fun initView(root: View) {
        super.initView(root)
        binding?.apply {
            refresh.apply {
                setOnRefreshListener {
                    viewModel.dispatch(MainAction.LoadConsoleList)
                }
            }
            list.apply {
                adapter = this@ConsoleSelectFragment.adapter
            }
        }
    }

    override fun initViewModel(viewLifecycleOwner: LifecycleOwner) {
        super.initViewModel(viewLifecycleOwner)
        viewModel.uiData.observeState(viewLifecycleOwner, MainData::consoleListLoad) {
            when (it) {
                is BaseLoad.Error -> {
                    binding?.refresh?.isRefreshing = false
                }
                BaseLoad.Loading -> {
                    binding?.refresh?.isRefreshing = true
                }
                is BaseLoad.Success -> {
                    binding?.refresh?.isRefreshing = false
                    adapter.setList(it.data)
                }
                null -> {}
            }
        }
    }
}