package ren.imyan.kirby.ui.cheatcode

import android.content.Context
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseUIActivity
import ren.imyan.base.startActivity
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.databinding.ActivityCheatcodeBinding


/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 16:36
 * @website https://imyan.ren
 */
class CheatCodeActivity : BaseUIActivity<ActivityCheatcodeBinding, CheatCodeViewModel>() {

    companion object {
        fun actionStart(context: Context, game: ResItem) {
            startActivity<CheatCodeActivity>(context) {
                this.putExtra("game", game)
            }
        }
    }

    override fun initViewModel(): CheatCodeViewModel =
        ViewModelProvider(
            this,
            CheatCodeViewModelFactory(intent.getParcelableExtra("game"))
        )[CheatCodeViewModel::class.java]

    override fun initBinding(): ActivityCheatcodeBinding = ActivityCheatcodeBinding.inflate(
        layoutInflater
    )

    override fun initToolbar(): Pair<Toolbar, *> =
        Pair(binding.toolbarLayout.toolbar, viewModel.game.title)
}