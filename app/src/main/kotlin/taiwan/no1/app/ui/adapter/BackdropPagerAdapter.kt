package taiwan.no1.app.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 *
 * @author  Jieyi
 * @since   2/24/17
 */

class BackdropPagerAdapter(val lists: List<View>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = this.lists.size

    // FIXME: 4/2/17 There are only "3" pics will crash.
    override fun instantiateItem(container: ViewGroup, position: Int): Any =
            this.lists[position].also { container.addView(it) }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}