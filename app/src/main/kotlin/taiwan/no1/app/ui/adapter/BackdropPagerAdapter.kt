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

    override fun instantiateItem(container: ViewGroup, position: Int): Any =
            this.lists[position].apply { container.addView(this) }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}