package ren.imyan.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import ren.imyan.language.ContextWrapper.Companion.wrap
import ren.imyan.language.LanguageUtil
import ren.imyan.theme.ThemeManager

abstract class BaseUIActivity<T : ViewBinding, B : ViewModel> : BaseActivity() {

    private var _binding: T? = null
    private var _viewModel: B? = null

    val binding get() = _binding!!
    val viewModel get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getThemeManager().setAppTheme()
        _binding = initBinding()
        _viewModel = initViewModel()
    }

    abstract fun initViewModel(): B

    abstract fun initBinding(): T

    fun setToolBarTitle(@StringRes titleId: Int) {
        supportActionBar?.setTitle(titleId);
    }

    fun setToolBarTitle(title: CharSequence) {
        supportActionBar?.title = title;
    }

    fun getThemeManager() : ThemeManager = ThemeManager(this)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun attachBaseContext(newBase: Context?) {
        //如果不使用工具类也可以在这里处理好 Locale 传入
        val context = newBase?.let {
            wrap(newBase, LanguageUtil.getLocale(newBase))
        }
        super.attachBaseContext(context)
    }
}