package ren.imyan.base

import android.app.Activity
import java.util.*

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2020-12-05 17:40
 * @website https://imyan.ren
 */
object ActivityCollector {

    private val activities = Stack<Activity>()

    @JvmStatic
    fun addActivity(activity: Activity) = activities.add(activity)

    @JvmStatic
    fun removeActivity(activity: Activity) = activities.remove(activity)

    @JvmStatic
    fun currActivity(): Activity = activities.lastElement()

    @JvmStatic
    fun finishByName(cls: Activity?) {
        val activityList: MutableList<Activity> = ArrayList()
        for (activity in activities) {
            if (activity.javaClass == cls) {
                activityList.add(activity)
            }
        }
        activityList.let { activities.removeAll(it) }
        for (activity in activityList) {
            activity.finish()
        }
    }

    @JvmStatic
    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }
}