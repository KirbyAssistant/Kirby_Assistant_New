package ren.imyan.kirby.ui.game

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.R
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.ActivityGameListBinding
import ren.imyan.kirby.util.getLocalString

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
        setContentView(binding.root)
        initToolBar()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setToolBarTitle("${getLocalString(R.string.title_game_list)} ${viewModel.res.title}")
    }
}