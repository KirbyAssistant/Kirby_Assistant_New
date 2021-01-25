package ren.imyan.ktx

import android.content.Context
import android.widget.Toast

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 16:08
 * @website https://imyan.ren
 */
fun toast(context: Context, string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, string, duration).show()
}

fun String.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    toast(context, this, duration)
}

fun Number.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    toast(context, this.toString(), duration)
}