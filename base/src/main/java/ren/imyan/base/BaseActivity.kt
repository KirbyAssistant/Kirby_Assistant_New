package ren.imyan.base

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Method


/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 16:54
 * @website https://imyan.ren
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
        Log.d("BaseActivity", "This Activity is ${javaClass.simpleName}")
    }

    override fun recreate() {
        startActivity(Intent().setComponent(intent.component))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}