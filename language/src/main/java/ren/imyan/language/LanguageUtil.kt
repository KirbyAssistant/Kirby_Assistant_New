package ren.imyan.language

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import ren.demo.util.LocaleUtils.getSysLocale
import java.util.*


/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-11-29 16:28
 * @website https://imyan.ren
 */
object LanguageUtil {

    fun showLanguageDialog(context: Context, title: String, callback: () -> Unit) {
        val lanList = arrayOf(
                "Auto", "简体中文", "繁體中文（台灣）", "ENGLISH"
        )
        AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(
                        lanList,
                        getCheckedItem(context)
                ) { dialog, which ->
                    val lan = when (which) {
                        0 -> "auto"
                        1 -> "zh-rCN"
                        2 -> "zh-rTW"
                        3 -> "en"
                        else -> "auto"
                    }
                    setLanguage(context, lan)
                    dialog.dismiss()
                    callback()
                }
                .create()
                .show()
    }

    /**
     * 设置语言的值
     * @param context 上下文
     * @param lan 需要设置的语言
     */
    fun setLanguage(context: Context, lan: String) {
        context.getSharedPreferences("settings", 0).edit {
            putString("language", lan)
            this.commit()
        }
    }

    /**
     * 获取应用于选择语言对话框的 checkedItem
     */
    fun getCheckedItem(context: Context): Int =
            when (context.getSharedPreferences("settings", 0).getString("language", "cn")) {
                "auto" -> 0
                "zh-rCN" -> 1
                "zh-rTW" -> 2
                "en" -> 3
                else -> 0
            }

    /**
     * 获取当前设置的 Locale
     */
    fun getLocale(context: Context): Locale =
        when (context.getSharedPreferences("settings", 0).getString("language", "cn")) {
            "auto" -> getSysLocale()
            "zh-rCN" -> Locale("zh", "CN")
            "zh-rTW" -> Locale("zh", "TW")
            "en" -> Locale("en")
            else -> getSysLocale()
        }
}