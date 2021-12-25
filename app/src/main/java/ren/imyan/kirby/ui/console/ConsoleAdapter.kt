package ren.imyan.kirby.ui.console

import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ren.imyan.kirby.R
import ren.imyan.kirby.databinding.ItemResBinding
import ren.imyan.kirby.net.response.ConsoleResponse

class ConsoleAdapter :
    BaseQuickAdapter<ConsoleResponse, BaseDataBindingHolder<ItemResBinding>>(R.layout.item_res) {
    override fun convert(holder: BaseDataBindingHolder<ItemResBinding>, item: ConsoleResponse) {
        holder.dataBinding?.apply {
            image.load(item.image)
            title.text = item.title
        }
    }
}