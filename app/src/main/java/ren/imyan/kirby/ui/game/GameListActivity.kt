package ren.imyan.kirby.ui.game

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.data.model.Game
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.ActivityGameListBinding
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.resources.ResListAdapter
import ren.imyan.kirby.util.getLocalString
import ren.imyan.ktx.toast

class GameListActivity() : BaseUIActivity<ActivityGameListBinding, GameListViewModel>() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        initView()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setToolBarTitle("${getLocalString(R.string.title_game_list)} ${viewModel.res.title}")
    }

    private fun initView() {
        viewModel.gameData.observe(this) {
            showListData(it)
        }
        viewModel.gameDataState.observe(this) {
            showErrorMessage(it, viewModel.res.tag)
        }
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