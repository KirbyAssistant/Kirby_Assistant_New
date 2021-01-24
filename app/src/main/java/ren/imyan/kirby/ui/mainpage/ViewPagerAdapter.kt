package ren.imyan.kirby.ui.mainpage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter constructor (
    fragmentManager: FragmentManager,
    behavior: Int,
    private val fragmentList: List<Fragment>
) :
    FragmentPagerAdapter(fragmentManager, behavior) {

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount() = fragmentList.size
}