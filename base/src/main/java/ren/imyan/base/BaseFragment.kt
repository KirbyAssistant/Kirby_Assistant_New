package ren.imyan.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * 封装一个有懒加载的 Fragment
 * @param T 传入泛型的 ViewBinding
 */
abstract class BaseFragment<T : ViewBinding, B : ViewModel> : Fragment() {
    private var isViewOK = false //是否完成 View 初始化
    private var isFirst = true //是否为第一次加载

    private var _binding: T? = null
    private var _viewModel: B? = null

    val binding get() = _binding!!
    val viewModel get() = _viewModel!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 完成 initView 后改变view的初始化状态为完成
        _binding = initBinding(inflater, container)
        isViewOK = true
        return _binding!!.root
    }

    /**
     * 初始化 ViewModel
     */
    abstract fun initViewModel(): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = initViewModel()
        initView()
    }

    /**
     * 传入对应的 ViewBinding
     */
    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): T

    /**
     * fragment 初始化 view 的方法
     */
    abstract fun initView()

    override fun onResume() {
        super.onResume()
        //在 onResume 进行数据懒加载
        initLoadData()
    }

    private fun initLoadData() {
        if (isViewOK && isFirst) {
            //加载数据时判断是否完成view的初始化，以及是不是第一次加载此数据
            loadDate()
            //加载第一次数据后改变状态，后续不再重复加载
            isFirst = false
        }
    }

    /**
     * fragment 实现懒加载的方法，即在这里加载数据
     */
    abstract fun loadDate()

    /**
     * 释放数据
     */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}