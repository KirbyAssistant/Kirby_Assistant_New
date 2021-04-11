package ren.imyan.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-21 15:54
 * @website https://imyan.ren
 */

abstract class BaseAdapter<T, B : ViewDataBinding>(
    private var dataList: MutableList<T>,
    private var layoutRes: Int
) : RecyclerView.Adapter<BaseAdapter<T, B>.ViewHolder>() {

    private var itemClickListener: ((itemData: T, itemBinding: B) -> Unit)? = null
    private var isLoading = false
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(inflater, layoutRes, parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun setNewData(newItems: List<T>) {
        dataList.clear()
        dataList.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: B) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemData: T) {
            bindItem(itemBinding, itemData)
            itemBinding.executePendingBindings()
            itemClickListener?.invoke(itemData, itemBinding)
            bindAfterExecute(itemBinding, itemData)
        }

    }

    abstract fun bindItem(itemBinding: B, itemData: T)

    open fun bindAfterExecute(itemBinding: B, itemData: T) {}

    fun setItemClick(click: (itemData: T, itemBinding: B) -> Unit) {
        itemClickListener = click
    }

    fun setLoadMoreListener(recyclerView: RecyclerView, loader: () -> Unit) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(ry: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(ry, dx, dy)
                val layoutManager = ry.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItem >= totalItemCount - 10 && dy > 0) {
                    if (!isLoading) {
                        loader()
                        isLoading = true
                    }
                }
            }

            override fun onScrollStateChanged(ry: RecyclerView, newState: Int) {
                super.onScrollStateChanged(ry, newState)
                val layoutManager = ry.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItem >= totalItemCount - 10 && newState == 0) {
                    if (!isLoading) {
                        loader()
                        isLoading = true
                    }
                }
            }
        })
    }

    fun setLoadComplete() {
        isLoading = false
    }

    fun setLoadFail() {
        isLoading = false
    }

    fun addData(newDataList: List<T>) {
        dataList.addAll(newDataList)
        notifyItemRangeInserted(dataList.size - newDataList.size, newDataList.size)
    }
}