package ren.imyan.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import ren.imyan.language.ContextWrapper.Companion.wrap
import ren.imyan.language.LanguageUtil
import ren.imyan.theme.ThemeManager

open class BaseUIActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager(this).setAppTheme()
    }

    //    companion object {
//        /**
//         * 静态启动 MainActivity 的方法
//         * @param context 启动的上下文
//         */
//        abstract fun actionStart(context: Context);
//    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun attachBaseContext(newBase: Context?) {
        //如果不使用工具类也可以在这里处理好 Locale 传入
        val context = newBase?.let {
            wrap(newBase, LanguageUtil.getLocale(newBase))
        }
        super.attachBaseContext(context)
    }
}