package ren.imyan.kirby.ui.game

import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import ren.imyan.kirby.common.ktx.binding
import ren.imyan.kirby.common.ktx.observeState
import ren.imyan.kirby.core.BaseActivity
import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.databinding.FragmentOnlyListBinding
import ren.imyan.kirby.net.response.GameResponse

class GameActivity : BaseActivity() {
    private val binding by binding(FragmentOnlyListBinding::inflate)
    private val viewModel by viewModels<GameViewModel>()

    private val console by lazy { intent.getStringExtra("console") ?: "" }

    private val adapter by lazy {
        GameListAdapter().apply {
            setOnItemClickListener { adapter, _, position ->
                val ele = adapter.data[position] as GameResponse
                val versionArray = arrayOfNulls<String>(ele.downloadLink?.size ?: 0)
                ele.downloadLink?.keys?.forEachIndexed { index, s ->
                    when (s) {
                        "jp" -> versionArray[index] = "日版"
                        "us" -> versionArray[index] = "美版"
                        "cn" -> versionArray[index] = "中文版"
                        else -> versionArray[index] = s
                    }
                }
                MaterialAlertDialogBuilder(this@GameActivity)
                    .setTitle("请选择您需要的语言版本")
                    .setItems(versionArray) { dialog, which ->
                        Intent(Intent.ACTION_VIEW).apply {
                            addCategory(Intent.CATEGORY_BROWSABLE)
                            data = Uri.parse(ele.downloadLink?.values?.toList()?.get(which))
                            startActivity(this)
                        }
                        dialog.dismiss()
                    }.show()
            }
        }
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
            refresh.apply {
                setOnRefreshListener {
                    viewModel.dispatch(GameAction.LoadGameList(console))
                }
            }
            list.apply {
                adapter = this@GameActivity.adapter
            }
        }
    }

    override fun loadSingleData() {
        super.loadSingleData()
        viewModel.dispatch(GameAction.LoadGameList(console))
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.uiData.observeState(this, GameData::gameListLoad) {
            when (it) {
                is BaseLoad.Error -> {
                    binding.refresh.isRefreshing = false
                }
                BaseLoad.Loading -> {
                    binding.refresh.isRefreshing = true
                }
                is BaseLoad.Success -> {
                    binding.refresh.isRefreshing = false
                    adapter.setList(it.data)
                }
                null -> {}
            }
        }
    }
}