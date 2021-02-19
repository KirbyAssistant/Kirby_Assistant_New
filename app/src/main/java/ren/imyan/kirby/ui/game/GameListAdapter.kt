package ren.imyan.kirby.ui.game

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import cn.ednureblaze.glidecache.GlideCache
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.cache.DiskCache
import com.bumptech.glide.load.engine.cache.SafeKeyGenerator
import com.bumptech.glide.signature.EmptySignature
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*
import ren.imyan.base.ActivityCollector
import ren.imyan.kirby.R
import ren.imyan.kirby.core.currActivity
import ren.imyan.kirby.data.model.Game
import ren.imyan.kirby.databinding.ItemResBinding
import ren.imyan.kirby.ui.ResViewHolder
import ren.imyan.ktx.saveBitmap2Galley
import ren.imyan.ktx.toast
import java.io.File
import java.io.IOException


/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-10 19:38
 * @website https://imyan.ren
 */
class GameListAdapter(private val data: List<Game>) :
    RecyclerView.Adapter<ResViewHolder>() {

    companion object {
        val JP_VERSION =
            ActivityCollector.currActivity().resources.getString(R.string.dia_jp)
        val US_VERSION =
            ActivityCollector.currActivity().resources.getString(R.string.dia_us)
        val CN_VERSION =
            ActivityCollector.currActivity().resources.getString(R.string.dia_cn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResViewHolder {
        val binding = ItemResBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResViewHolder, position: Int) {
        val ele = data[position]

        val animation = AnimationUtils.loadAnimation(
            ActivityCollector.currActivity(),
            R.anim.anim_recycler_item_show
        )
        val alphaAnimation = AlphaAnimation(0.1f, 1.0f)
        alphaAnimation.duration = 500

        holder.itemRes.startAnimation(animation)
        holder.image.animation = alphaAnimation
        holder.blurImage.animation = alphaAnimation
        holder.name.animation = alphaAnimation

        holder.name.text = ele.title
        Glide.with(ActivityCollector.currActivity())
            .load(ele.image)
            .into(holder.image)

        holder.linearLayout.setOnClickListener {
            val versionList = ArrayList<String>()
            for ((key) in ele.downloadLink) {
                when (key) {
                    "jp" -> versionList.add(JP_VERSION)
                    "us" -> versionList.add(US_VERSION)
                    "cn"
                    -> versionList.add(CN_VERSION)
                    else -> versionList.add(key)
                }
            }
            versionList.add("保存封面图片")
            MaterialAlertDialogBuilder(currActivity)
                .setTitle(R.string.game_download_game)
                .setItems(versionList.toArray(arrayOfNulls<CharSequence>(versionList.size))) { _, which ->
                    if (versionList[which] == versionList.last()) {
                        (currActivity as GameListActivity).requestPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        (currActivity as GameListActivity).requestPermissionState.observe(
                            currActivity as GameListActivity
                        ) {
                            if (it) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    val bitmap = async {
                                        GlideCache.getGlideBitmap(currActivity, ele.image)
                                    }
                                    val state = currActivity.saveBitmap2Galley(
                                            bitmap.await(),
                                            "kirbyassistant",
                                            ele.title
                                        )

                                    if (state.isOk) {
                                        toast(currActivity, "保存成功，路径 ${state.path}")
                                    } else {

                                    }
                                }
                            } else {
                                toast(currActivity, "未授予权限，无法保存")
                            }
                        }
                    } else {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data =
                            Uri.parse(ele.downloadLink[ele.downloadLink.keys.toList()[which]])
                        currActivity.startActivity(intent)
                    }
                }.show()
        }
    }

    override fun getItemCount(): Int = data.size
}