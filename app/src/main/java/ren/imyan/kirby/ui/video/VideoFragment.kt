package ren.imyan.kirby.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ren.imyan.base.BaseFragment
import ren.imyan.kirby.databinding.FragmentVideoBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 19:27
 * @website https://imyan.ren
 */
class VideoFragment : BaseFragment<FragmentVideoBinding, VideoViewModel>() {
    override fun initViewModel(): VideoViewModel =
        ViewModelProvider(this)[VideoViewModel::class.java]

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoBinding = FragmentVideoBinding.inflate(inflater, container, false)

    override fun initView() {
    }

    override fun loadDate() {
    }
}