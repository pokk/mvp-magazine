package taiwan.no1.app.ui.adapter

import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.ViewGroup


/**
 * Lazy Pager Adapter.
 * NOTE: https://github.com/lianghanzhen/LazyViewPager
 * 
 * @author  Jieyi
 * @since   3/19/17
 */

abstract class LazyPagerAdapter<T>: PagerAdapter() {
    protected var lazyItems = SparseArray<T>()
    /**
     * Get the current item.
     *
     * @return the current item.
     */
    var currentItem: T? = null
        private set

    /**
     * Add lazy item to container.
     *
     * @param container [LazyViewPager]
     * @param position the position that the item added to.
     * @return the item added.
     */
    protected abstract fun addLazyItem(container: ViewGroup, position: Int): T?

    /**
     * Get the lazy item.
     *
     * @param container [LazyViewPager]
     * @param position the position of lazy item.
     * @return the lazy item.
     */
    protected abstract fun getItem(container: ViewGroup, position: Int): T

    /**
     * Whether the item is lazily or not.
     *
     * @param position the position of item.
     * @return the item is lazily.
     */
    fun isLazyItem(position: Int): Boolean = lazyItems.get(position) != null

    /**
     * Call [LazyPagerAdapter.addLazyItem] to prevent [LazyViewPager.onPageScrolled] not working
     * when the offset of [LazyViewPager] is too big.
     */
    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        currentItem = addLazyItem(container, position)
    }
}