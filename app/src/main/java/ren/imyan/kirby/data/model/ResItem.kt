package ren.imyan.kirby.data.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.parcelize.Parcelize
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.ui.cheatcode.CheatCodeActivity
import ren.imyan.kirby.ui.game.GameListActivity

@Parcelize
data class ResItem(
    val title: String,
    val imageUrl: String,
    val tag: String,
    val type: String = "notype"
) : Parcelable, BaseObservable() {
    fun jump(type: String) {
        when (type) {
            "console" -> GameListActivity.actionStart(currActivity, this)
            "cheatcode" -> CheatCodeActivity.actionStart(currActivity, this)
        }
    }
}