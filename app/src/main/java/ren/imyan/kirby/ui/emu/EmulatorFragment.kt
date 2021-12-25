package ren.imyan.kirby.ui.emu

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight
import org.koin.core.qualifier.named
import ren.imyan.kirby.R
import ren.imyan.kirby.common.ktx.binding
import ren.imyan.kirby.common.ktx.get
import ren.imyan.kirby.common.ktx.observeState
import ren.imyan.kirby.core.BaseFragment
import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.databinding.FragmentOnlyListBinding
import ren.imyan.kirby.ui.MainAction
import ren.imyan.kirby.ui.MainData
import ren.imyan.kirby.ui.MainViewModel

class EmulatorFragment : BaseFragment(R.layout.fragment_only_list) {
    private val binding by binding(FragmentOnlyListBinding::bind)
    private val viewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy {
        EmulatorAdapter().apply {
//            addHeaderView(get(named("statusBarPadding")))
            setOnItemClickListener { _, _, _ ->
                MaterialAlertDialogBuilder(requireActivity())
                    .setTitle("很抱歉！这部分的资源出了些问题")
                    .setItems(arrayOf("请点击下方的选项暂时跳转到网盘下载，提取密码为 kirby","手机模拟器","电脑模拟器")) { dialog, which ->
                        Intent(Intent.ACTION_VIEW).apply {
                            addCategory(Intent.CATEGORY_BROWSABLE)
                            data = Uri.parse(if(which == 1) "https://wwe.lanzouo.com/b01uucqgb" else "https://wwe.lanzouo.com/b01uucqid" )
                            startActivity(this)
                        }
                        dialog.dismiss()
                    }.show()
            }
        }
    }

    override fun initView(root: View) {
        super.initView(root)
        binding?.apply {
            refresh.apply {
                setOnRefreshListener {
                    viewModel.dispatch(MainAction.LoadEmulatorList)
                }
            }
            list.apply {
                adapter = this@EmulatorFragment.adapter
            }
        }
    }

    override fun initViewModel(viewLifecycleOwner: LifecycleOwner) {
        super.initViewModel(viewLifecycleOwner)
        viewModel.uiData.observeState(viewLifecycleOwner, MainData::emulatorListLoad) {
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