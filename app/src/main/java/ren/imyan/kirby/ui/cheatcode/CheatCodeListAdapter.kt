package ren.imyan.kirby.ui.cheatcode

import ren.imyan.base.BaseAdapter
import ren.imyan.kirby.R
import ren.imyan.kirby.RecyclerAnimation
import ren.imyan.kirby.data.model.CheatCode
import ren.imyan.kirby.databinding.ItemCheatcodeBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-21 21:06
 * @website https://imyan.ren
 */
class CheatCodeListAdapter(data: List<CheatCode>) : BaseAdapter<CheatCode, ItemCheatcodeBinding>(
    data.toMutableList(),
    R.layout.item_cheatcode
) {
    override fun bindItem(itemBinding: ItemCheatcodeBinding, itemData: CheatCode) {
        itemBinding.root.startAnimation(RecyclerAnimation.itemAnimation)
        itemBinding.root.animation = RecyclerAnimation.alphaAnimation
        itemBinding.cheatcode = itemData
    }
}