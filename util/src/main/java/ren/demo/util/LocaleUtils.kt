package ren.demo.util

import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 17:02
 * @website https://imyan.ren
 */
object LocaleUtils {
    /**
     * 获取当前系统的 Locale
     */
    fun getSysLocale(): Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LocaleList.getDefault()[0]
    } else {
        Locale.getDefault()
    }
}