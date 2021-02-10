package ren.imyan.base

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
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
    private var isSet = true

    val binding get() = _binding!!
    val viewModel get() = _viewModel!!

    fun dotSetView() {
        this.isSet = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getThemeManager().setAppTheme()
        _binding = initBinding()
        _viewModel = initViewModel()
        if (isSet) {
            setContentView(binding.root)
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window?.decorView?.let {
//                it.systemUiVisibility = (it.systemUiVisibility
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//            }
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                window?.decorView?.post {
//                    if (window.decorView.rootWindowInsets?.systemWindowInsetBottom ?: 0 < Resources.getSystem().displayMetrics.density * 40) {
//                        window.navigationBarColor = Color.TRANSPARENT
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                            window.navigationBarDividerColor = Color.TRANSPARENT
//                        }
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                            window.isNavigationBarContrastEnforced = false
//                            window.isStatusBarContrastEnforced = false
//                        }
//                    }
//                }
//            }
//        }
    }

    abstract fun initViewModel(): B

    abstract fun initBinding(): T

    fun setToolBarTitle(@StringRes titleId: Int) {
        supportActionBar?.setTitle(titleId);
    }

    fun setToolBarTitle(title: CharSequence) {
        supportActionBar?.title = title;
    }

    fun getThemeManager(): ThemeManager = ThemeManager(this)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun attachBaseContext(newBase: Context?) {
        //如果不使用工具类也可以在这里处理好 Locale 传入
        val context = newBase?.let {
            wrap(newBase, LanguageUtil.getLocale(newBase))
        }
        super.attachBaseContext(context)
    }
}