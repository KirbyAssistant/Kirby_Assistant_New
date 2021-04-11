package ren.imyan.kirby.util

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import ren.imyan.base.BaseAdapter
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.ui.cheatcode.CheatCodeViewModel
import ren.imyan.kirby.ui.resources.ResViewModel
import ren.imyan.kirby.ui.video.VideoViewModel

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 12:50
 * @website https://imyan.ren
 */
fun <E, ViewBinding : ViewDataBinding, T : BaseAdapter<E, ViewBinding>> ViewpagerResBinding.showListData(
    adapter: T,
    spanCount: Int = 1,
    block: ((E, ViewBinding) -> Unit) = { _, _ -> }
) {
    this.showDataList.layoutManager = GridLayoutManager(currActivity, spanCount)
    this.showDataList.adapter = adapter
    this.loadBar.visibility = View.GONE
    adapter.setItemClick { itemData, itemBinding ->
        block(itemData, itemBinding)
    }
}

@SuppressLint("SetTextI18n")
fun <T> ViewpagerResBinding.showErrorMessage(
    error: String,
    type: String,
    viewModel: T
) {
    this.loadBar.visibility = View.GONE
    this.errMsg.visibility = View.VISIBLE
    this.errMsg.text = "$error\n请点击重试或截图告知开发者"
    this.root.setOnClickListener {
        this.errMsg.visibility = View.GONE
        this.loadBar.visibility = View.VISIBLE
        if (viewModel as? ResViewModel != null) {
            viewModel.getAgainData(type)
        }
        if(viewModel as? VideoViewModel != null){
            viewModel.getAgainData()
        }
    }
}