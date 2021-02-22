package ren.imyan.kirby.util

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import ren.imyan.base.BaseAdapter
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.databinding.ViewpagerResBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 12:50
 * @website https://imyan.ren
 */
fun <E, V : ViewDataBinding, T : BaseAdapter<E, V>> ViewpagerResBinding.showListData(
    adapter: T,
    spanCount: Int = 1,
    block: ((E, V) -> Unit)? = null
) {
    this.showDataList.layoutManager = GridLayoutManager(currActivity, spanCount)
    this.showDataList.adapter = adapter
    this.loadBar.visibility = View.GONE
    adapter.setItemClick { itemData, itemBinding ->
        block?.let { it(itemData, itemBinding) }
    }
}
