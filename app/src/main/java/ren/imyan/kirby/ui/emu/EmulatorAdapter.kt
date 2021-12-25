package ren.imyan.kirby.ui.emu

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight
import ren.imyan.kirby.R
import ren.imyan.kirby.databinding.ItemResBinding
import ren.imyan.kirby.net.response.EmulatorResponse


class EmulatorAdapter :
    BaseQuickAdapter<EmulatorResponse, BaseDataBindingHolder<ItemResBinding>>(R.layout.item_res) {
    override fun convert(holder: BaseDataBindingHolder<ItemResBinding>, item: EmulatorResponse) {
        holder.dataBinding?.apply {
            image.load(item.image)
            title.text = item.title
        }
    }
}