package ren.imyan.ktx

import android.widget.TextView

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 16:02
 * @website https://imyan.ren
 */
fun TextView.checkBlank(doSomething: () -> Unit = {}): String {
    val text = this.text.toString()
    if (text.isBlank()) {
        doSomething()
        return ""
    }
    return text
}