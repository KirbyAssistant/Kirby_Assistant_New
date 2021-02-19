package ren.imyan.kirby.ui.game

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import cn.ednureblaze.glidecache.GlideCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.data.model.Game
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.ActivityGameListBinding
import ren.imyan.kirby.util.getLocalString
import ren.imyan.ktx.saveBitmap2Galley
import ren.imyan.ktx.toast

class GameListActivity() : BaseUIActivity<ActivityGameListBinding, GameListViewModel>() {

    private lateinit var save: ActivityResultLauncher<String>
    private var isSave = false
    private lateinit var ele: Game

    companion object {
        fun actionStart(context: Context, res: ResItem) {
            startActivity<GameListActivity>(context) {
                this.putExtra("res", res)
            }
        }
    }

    override fun initViewModel(): GameListViewModel =
        ViewModelProvider(
            this,
            GameListViewModelFactory(intent.getParcelableExtra("res"))
        )[GameListViewModel::class.java]

    override fun initBinding(): ActivityGameListBinding =
        ActivityGameListBinding.inflate(layoutInflater)

    override fun initToolbar(): Pair<Toolbar, *> = Pair(
        binding.toolbarLayout.toolbar,
        "${getLocalString(R.string.title_game_list)} ${viewModel.res.title}"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        save = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    val bitmap = withContext(Dispatchers.IO) {
                        GlideCache.getGlideBitmap(currActivity, ele.image)
                    }
                    val state = currActivity.saveBitmap2Galley(
                        bitmap,
                        "kirbyassistant",
                        ele.title
                    )
                    if (state.isOk) {
                        toast(currActivity, "保存成功，路径 ${state.path}")
                    } else {

                    }
                }
            } else {
                toast(currActivity, "未授予权限，无法保存")
            }
        }
    }

    private fun initView() {
        viewModel.gameData.observe(this) {
            showListData(it)
        }
        viewModel.gameDataState.observe(this) {
            showErrorMessage(it, viewModel.res.tag)
        }
    }

    fun saveImage(ele: Game) {
        this.ele = ele
        save.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun showListData(data: List<Game>) {
        binding.gameList.layoutManager = GridLayoutManager(this, 1)
        binding.gameList.adapter = GameListAdapter(data)
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