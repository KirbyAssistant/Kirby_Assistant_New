package ren.imyan.kirby.ui.game

import android.content.Intent
import android.net.Uri
import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ren.imyan.kirby.R
import ren.imyan.kirby.common.ktx.get
import ren.imyan.kirby.databinding.ItemResBinding
import ren.imyan.kirby.net.response.GameResponse

class GameListAdapter :
    BaseQuickAdapter<GameResponse, BaseDataBindingHolder<ItemResBinding>>(R.layout.item_res) {
    override fun convert(holder: BaseDataBindingHolder<ItemResBinding>, item: GameResponse) {
        holder.dataBinding?.apply {
            image.load(item.image)
            title.text = item.title
        }
    }
}