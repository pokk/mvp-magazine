package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @since   1/6/17
 */

class MainFragmentViewPagerAdapter constructor(val context: Context,
                                               val fragmentManager: FragmentManager,
                                               val fragmentList: List<Fragment>):
        FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = this.fragmentList[position]

    override fun getCount(): Int = this.fragmentList.size

    override fun finishUpdate(container: ViewGroup) {
        // FIXED: 2/17/17 When rotate the phone, here will cause crashing issue.
        // FIXED: http://stackoverflow.com/questions/41650721/attempt-to-invoke-virtual-method-android-os-handler-android-support-v4-app-frag
        try {
            super.finishUpdate(container)
        }
        catch (exception: Exception) {
            AppLog.e(exception)
        }
    }
}