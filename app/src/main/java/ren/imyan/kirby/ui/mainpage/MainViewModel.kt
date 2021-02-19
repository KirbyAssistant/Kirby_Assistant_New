package ren.imyan.kirby.ui.mainpage

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import ren.imyan.kirby.ui.resources.ResFragment
import ren.imyan.kirby.ui.video.VideoFragment

class MainViewModel : ViewModel() {

    val fragmentList: List<Fragment>
        get() = _fragmentList

    private val _fragmentList: MutableList<Fragment> by lazy {
        ArrayList()
    }

    init {
        _fragmentList.addAll(loadFragments())
    }

    private fun loadFragments(): ArrayList<Fragment> =
        arrayListOf(
            ResFragment(),
            VideoFragment(),
            Fragment()
        )
}