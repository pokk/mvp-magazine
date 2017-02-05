package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 *
 * @author  Jieyi
 * @since   1/6/17
 */

class MainViewPager constructor(val context: Context,
                                val fragmentManager: FragmentManager,
                                val fragmentList: List<Fragment>):
        FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = this.fragmentList[position]

    override fun getCount(): Int = this.fragmentList.size
}