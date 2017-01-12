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
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(this.context) }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = 3

    override fun destroyItem(container: View?, position: Int, `object`: Any?) {
        (container as ViewPager).removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = this.layoutInflater.inflate(R.layout.fragment_movie_list, null, false)

        container.addView(view)
        return view
    }
}