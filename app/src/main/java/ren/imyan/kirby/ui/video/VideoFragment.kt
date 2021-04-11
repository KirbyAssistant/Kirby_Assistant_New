package ren.imyan.kirby.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.databinding.ViewpagerResBinding
import ren.imyan.kirby.util.showErrorMessage
import ren.imyan.kirby.util.showListData

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 19:27
 * @website https://imyan.ren
 */
class VideoFragment : BaseFragment<ViewpagerResBinding, VideoViewModel>() {
    override fun initViewModel(): VideoViewModel =
        ViewModelProvider(this)[VideoViewModel::class.java]

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewpagerResBinding = ViewpagerResBinding.inflate(inflater, container, false)

    override fun initView() {
    }

    override fun loadDate() {
        viewModel.videoListData.data.observe(this) {
            binding.showListData(VideoListAdapter(it), 2)
        }
        viewModel.videoListData.state.observe(this) {
            binding.showErrorMessage(it, "video", viewModel)
        }
    }
}