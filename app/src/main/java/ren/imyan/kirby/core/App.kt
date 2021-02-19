package ren.imyan.kirby.core

import android.app.Activity
import android.app.Application
import android.content.Context
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobConfig
import com.oasisfeng.condom.CondomContext
import ren.imyan.base.ActivityCollector

val currActivity: Activity
    get() = ActivityCollector.currActivity()

class App : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
//        Bmob.initialize(CondomContext.wrap(this,"bmob"),"e39c2e15ca40b358b0dcc933236c1165")
    }
}