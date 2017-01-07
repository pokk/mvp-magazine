package taiwan.no1.app.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import taiwan.no1.app.R

/**
 *
 * @author  Jieyi
 * @since   12/28/16
 */

class BottomPagerAdapter(val context: Context): PagerAdapter() {

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 3
    }

    override fun destroyItem(container: View?, position: Int, `object`: Any?) {
        (container as ViewPager).removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(this.context).inflate(R.layout.fragment_movie_upcoming, null, false)

        container.addView(view)
        return view
    }
}