package ren.imyan.kirby.core

import android.app.Activity
import android.app.Application
import android.content.Context
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
    }
}