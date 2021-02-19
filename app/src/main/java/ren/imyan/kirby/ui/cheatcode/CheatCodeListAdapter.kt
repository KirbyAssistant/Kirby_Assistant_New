package ren.imyan.kirby.ui.cheatcode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ren.imyan.kirby.data.model.CheatCode
import ren.imyan.kirby.databinding.ItemCheatcodeBinding

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 18:25
 * @website https://imyan.ren
 */
class CheatCodeListAdapter(private val data: List<CheatCode>) :
    RecyclerView.Adapter<CheatCodeListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCheatcodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val item = binding.root
        val linearLayout = binding.LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCheatcodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ele = data[position]

        holder.binding.cheatcode = ele

    }

    override fun getItemCount(): Int = data.size
}