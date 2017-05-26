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

    override fun getCount(): Int = if (3 == this.lists.size) this.lists.size - 1 else this.lists.size

    // XXX: 4/9/17 Workaround to fix the problem of the size equal three will crash.
    // FIXME: 5/26/17 Here is not really workaround. It will crash when there are four pics.
    override fun instantiateItem(container: ViewGroup, position: Int): Any =
            this.lists[position].also { container.addView(it) }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}