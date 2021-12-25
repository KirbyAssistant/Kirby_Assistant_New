package ren.imyan.kirby.common.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import ren.imyan.kirby.common.ktx.get

fun toast(content: String) {
    Toast.makeText(get<Context>(), content, Toast.LENGTH_SHORT).show()
}

fun toast(@StringRes resId: Int) {
    toast(get<Context>().getString(resId))
}

@JvmName("toast1")
fun String.toast() {
    toast(this)
}

fun Any.toast() {
    toast(this.toString())
}