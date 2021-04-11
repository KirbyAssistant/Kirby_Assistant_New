package ren.imyan.kirby.ui.setting

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ren.imyan.kirby.R
import ren.imyan.kirby.util.AppInfoUtil.channel
import ren.imyan.kirby.util.AppInfoUtil.packageName
import ren.imyan.kirby.util.AppInfoUtil.versionCode
import ren.imyan.kirby.util.AppInfoUtil.versionName
import ren.imyan.kirby.util.FileUtil
import ren.imyan.kirby.util.getLocalString

/**
 * @author EndureBlaze/炎忍 https://github.com/EndureBlaze
 * @data 2021-04-05 18:01
 * @website https://imyan.ren
 */
class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        updatePreferenceSummary(
            "version",
            "(Build $versionCode) $versionName | $packageName | $channel"
        )

        findPreference<Preference>("apkCacheClean")?.summary =
            "${getLocalString(R.string.setting_apk_cache_clean_summary)} : ${
                FileUtil.getAutoFileOrFilesSize(
                    "${context?.externalCacheDir}/bmob/"
                )
            }"
        findPreference<Preference>("imageCacheClean")?.summary =
            "${getLocalString(R.string.setting_apk_cache_clean_summary)} : ${
                FileUtil.getAutoFileOrFilesSize(
                    "${context?.cacheDir}/${InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR}/"
                )
            }"
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        when (preference?.key) {
            "star" -> star()
//            "setLanguage" -> setLanguage()
            "apkCacheClean" -> cacheClean(preference)
            "imageCacheClean" -> cacheClean(preference)
//            "faq" -> faq()
            "qq_group" -> qqGroup()
            "tg_channel" -> openUrl("https://t.me/kirbyassistant")
            "github" -> openUrl("https://github.com/KirbyAssistant/Kirby_Assistant_Kotlin")
            "dev_coolapk" -> openUrl("http://www.coolapk.com/u/651880")
            "dev_github" -> openUrl("https://github.com/EndureBlaze")
            "dev_weibo" -> openUrl("https://weibo.com/runanjing")
            "dev_twitter" -> openUrl("https://twitter.com/nihaocun?s=09")
            "domain_name" -> openUrl("http://www.shaoxudong.com/")
            "translation_tw" -> openUrl("https://github.com/longxk2017")
            "translation_en_1" -> openUrl("https://tieba.baidu.com/home/main?un=难难难550")
            "translation_en_2" -> openUrl("https://tieba.baidu.com/home/main?un=光之耀西")
            "draw_icon_1" -> openUrl("http://www.coolapk.com/u/555883")
            "draw_icon_2" -> openUrl("http://www.coolapk.com/u/529718")
            "draw_icon_3" -> openUrl("https://tieba.baidu.com/home/main?un=★☆小伊布☆★")
            "video_author" -> openUrl("http://space.bilibili.com/13001252")
            "writer_help_faq" -> openUrl("http://www.coolapk.com/u/1157774")
        }

        return true
    }

    // 五星好评
    private fun star() {
        val star = Intent("android.intent.action.VIEW")
        star.data = Uri.parse("market://details?id=cn.endureblaze.kirby")
        star.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity?.startActivity(star)
    }

    private fun cacheClean(preference: Preference) {
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("确认清除？")
                .setPositiveButton(R.string.dia_yes) { _: DialogInterface?, _: Int ->
                    when (preference.key) {
                        "apkCacheClean" -> {
                            FileUtil.deleteDirectory("${context?.cacheDir}/bmob/", false)
                            preference.summary =
                                "${activity?.resources?.getString(R.string.setting_apk_cache_clean_summary)} : ${
                                    FileUtil.getAutoFileOrFilesSize(
                                        "${context?.externalCacheDir}/bmob/"
                                    )
                                }"
                        }
                        "imageCacheClean" -> {
                            FileUtil.deleteDirectory("${context?.cacheDir}/${InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR}/", false)
                            preference.summary =
                                "${activity?.resources?.getString(R.string.setting_apk_cache_clean_summary)} : ${
                                    FileUtil.getAutoFileOrFilesSize(
                                        "${context?.cacheDir}/${InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR}/"
                                    )
                                }"
                        }
                    }
                }
                .setNegativeButton(R.string.dia_cancel, null)
                .show()
        }
    }

//    private fun faq() {
//        when (LanguageUtil.getSysLanguage()) {
//            "zh-CN" -> openUrl("https://blog.endureblaze.cn/posts/ka_faq_cn/")
//            "zh-TW" -> openUrl("https://blog.endureblaze.cn/posts/ka_faq_tw/")
//            else -> openUrl("https://blog.endureblaze.cn/posts/ka_faq_en/")
//        }
//    }

    private fun qqGroup() {
        val key = "6j76WE8N9l378jnsWzmmUDv5HohOteHu"
        val joinQQ = Intent()
        joinQQ.data = Uri.parse(
            "mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D$key"
        )
        joinQQ.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            activity?.startActivity(joinQQ)
        } catch (e: Exception) {

        }
    }

    /**
     * 用浏览器打开一个链接
     * @param url 需要打开的链接
     */
    private fun openUrl(url: String) {
        val openUrl = Intent(Intent.ACTION_VIEW)
        openUrl.data = Uri.parse(url)
        openUrl.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity?.startActivity(openUrl)
    }

    private fun updatePreferenceSummary(preferenceKey: String, newSummary: String) {
        findPreference<Preference>("preferenceKey")?.summary = newSummary
    }
}