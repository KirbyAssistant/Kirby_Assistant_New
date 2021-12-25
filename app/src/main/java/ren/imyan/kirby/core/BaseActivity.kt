package ren.imyan.kirby.core

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window.ID_ANDROID_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX.statusBar
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import timber.log.Timber

interface UiEventImp<Event> {
    fun renderUiEvent(event: Event)
}

open class BaseActivity : AppCompatActivity() {

    private var firstResumed = false

    //监听系统边界变化
    //给Activity用的
    protected var fitSystemCallback: ((statusBarHeight: Int, navBarHeight: Int) -> Unit)? =
        { statusHeight, navHeight ->
            findViewById<ViewGroup>(ID_ANDROID_CONTENT).getChildAt(0)?.apply {
                updatePadding(top = statusHeight, bottom = navHeight)
            }
        }

    //给Fragment用的
    private var _fitSystemLiveData: MutableLiveData<Rect> = MutableLiveData()
    var fitSystemLiveData: LiveData<Rect> = _fitSystemLiveData

    private fun fitSystem(
        block: (statusBarHeight: Int, navBarHeight: Int) -> Unit
    ) {
        fitSystemCallback = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("当前 Activity ${javaClass.name}")
//        initSystemView()
        initView()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        if (!firstResumed) {
            firstResumed = true
            loadSingleData()
        }
        loadData()
    }

    protected open fun initSystemView() {
        statusBar {
            light = true
            fitWindow = false
        }
        navigationBar {
            fitWindow = true
        }
        setWindowInsetListener()
    }

    private fun adjustView(top: Int, bottom: Int) {
        _fitSystemLiveData.value = Rect(0, top, 0, bottom)
        fitSystemCallback?.invoke(top, bottom)
    }

    private fun setWindowInsetListener() {
        //这里使用decorView会导致状态栏/导航栏颜色无法修改，所以使用contentParent，难道decorView预先设置了一次InsetsListener?
        val contentParent = findViewById<View>(ID_ANDROID_CONTENT)
        ViewCompat.setOnApplyWindowInsetsListener(contentParent) { _, windInsetCompat ->
            val insets = windInsetCompat.getInsets(WindowInsetsCompat.Type.systemBars())
            adjustView(insets.top, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }
    }

    protected open fun initView() {}

    protected open fun initViewModel() {}

    protected open fun loadData() {}

    protected open fun loadSingleData() {}
}
