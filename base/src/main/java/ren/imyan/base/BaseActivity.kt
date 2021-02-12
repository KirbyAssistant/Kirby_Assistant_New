package ren.imyan.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 16:54
 * @website https://imyan.ren
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var requestPermission: ActivityResultLauncher<String>
    val requestPermissionState = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
        Log.d("BaseActivity", "This Activity is ${javaClass.simpleName}")

        requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                requestPermissionState.value = isGranted
            }
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