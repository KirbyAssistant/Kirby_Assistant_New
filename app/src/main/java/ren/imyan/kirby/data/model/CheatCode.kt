package ren.imyan.kirby.data.model

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.databinding.BaseObservable
import com.squareup.moshi.JsonClass
import ren.imyan.base.ActivityCollector
import ren.imyan.kirby.R
import ren.imyan.kirby.util.getLocalString
import ren.imyan.ktx.toast

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 18:21
 * @website https://imyan.ren
 */
@JsonClass(generateAdapter = true)
data class CheatCode(val info: String, val code: String) : BaseObservable() {
    fun copy(text: String) {
        val clipboardManager = ActivityCollector.currActivity()
            .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("CHEATCODE", text)
        clipboardManager.setPrimaryClip(clipData)
        toast(ActivityCollector.currActivity(), getLocalString(R.string.copy_success))
    }
}