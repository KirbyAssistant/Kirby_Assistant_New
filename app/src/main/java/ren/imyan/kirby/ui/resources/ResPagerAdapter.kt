package ren.imyan.kirby.ui.resources

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 17:14
 * @website https://imyan.ren
 */
class ResPagerAdapter(private val titleList: List<String>, private val viewList: List<View>) :
    PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = viewList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList[position])
        return viewList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(viewList[position])

    override fun getPageTitle(position: Int) = titleList[position]
}