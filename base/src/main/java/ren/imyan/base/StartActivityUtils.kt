package ren.imyan.base

import android.content.Context
import android.content.Intent

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 16:44
 * @website https://imyan.ren
 */

/**
 * 使用内联函数启动一个 Activity
 * @param context 上下文
 * @param T 需要启动的 Activity
 */
inline fun <reified T> startActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

/**
 * 使用内联函数启动一个 Activity
 * @param context 上下文
 * @param block Intent 需要的参数，例如传入一些 String
 * @param T 需要启动的 Activity
 */
inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

/**
 * 使用内联函数启动一个 Activity
 * @param context 上下文
 * @param block Intent 需要的参数，例如传入一些 String
 * @param overfun 最后还需要执行的一些函数
 * @param T 需要启动的 Activity
 */
inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit, overfun: () -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
    overfun()
}