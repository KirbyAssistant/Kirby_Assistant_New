package ren.imyan.kirby.ui

import android.content.Context
import android.os.Bundle
import ren.imyan.base.startActivity
import ren.imyan.base.BaseUIActivity
import ren.imyan.kirby.R

class MainActivity : BaseUIActivity() {

    companion object{
        fun actionStart(context:Context){
            startActivity<MainActivity>(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}