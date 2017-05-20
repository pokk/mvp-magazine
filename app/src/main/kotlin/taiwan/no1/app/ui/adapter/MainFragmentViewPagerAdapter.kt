package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import com.devrapid.kotlinknifer.AppLog

/**
 *
 * @author  Jieyi
 * @since   1/6/17
 */

class MainFragmentViewPagerAdapter constructor(val context: Context,
                                               val fragmentManager: FragmentManager,
                                               val fragmentList: List<Fragment>):
        LazyFragmentPagerAdapter(fragmentManager) {
    override fun getItem(container: ViewGroup, position: Int): Fragment {
        AppLog.w(container, position)
        return this.fragmentList[position]
    }

    override fun getCount(): Int = this.fragmentList.size

    override fun finishUpdate(container: ViewGroup) {
        // FIXED: 2/17/17 When rotate the phone, here will cause crashing issue.
        // FIXED: http://stackoverflow.com/questions/41650721/attempt-to-invoke-virtual-method-android-os-handler-android-support-v4-app-frag
        try {
            super.finishUpdate(container)
        }
        catch (exception: Exception) {
            AppLog.e("This is view pager bug!!!! shrug :<")
        }
    }
}