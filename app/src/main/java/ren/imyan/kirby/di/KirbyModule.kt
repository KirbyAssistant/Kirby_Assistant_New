package ren.imyan.kirby.di

import android.view.View
import android.widget.LinearLayout
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight
import org.koin.core.qualifier.named
import org.koin.dsl.module

val kirbyModule = module {
    single<View>(named("statusBarPadding")) {
        val linearLayout = LinearLayout(get())
        linearLayout.layoutParams = LinearLayout.LayoutParams(20, statusBarHeight)
        linearLayout
    }
}