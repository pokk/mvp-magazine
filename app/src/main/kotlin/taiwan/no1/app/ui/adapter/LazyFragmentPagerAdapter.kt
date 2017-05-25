package taiwan.no1.app.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.view.ViewGroup


/**
 *
 * @author  Jieyi
 * @since   3/19/17
 */

abstract class LazyFragmentPagerAdapter(private val fragmentManager: FragmentManager): LazyPagerAdapter<Fragment>() {
    companion object {
        private fun makeFragmentName(viewId: Int, id: Long): String = "android:switcher:$viewId:$id"
    }

    private var currTransaction: FragmentTransaction? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (null == this.currTransaction)
            this.currTransaction = this.fragmentManager.beginTransaction()

        val itemId = this.getItemId(position)

        // Do we already have this fragment?
        val name = makeFragmentName(container.id, itemId)
        var fragment = this.fragmentManager.findFragmentByTag(name)

        // FIXME: 4/25/17 : 4/25/17 這邊有問題，導致沒辦法 1 -> 4 -> 2 顯示畫面。
        if (null != fragment) {
            this.currTransaction?.attach(fragment)
        }
        else {
            fragment = this.getItem(container, position)
            if (fragment is Laziable)
                this.lazyItems.put(position, fragment)
            else
                this.currTransaction?.add(container.id, fragment, name)
        }
        if (fragment !== this.currentItem) {
            fragment.setMenuVisibility(false)
            fragment.userVisibleHint = false
        }

        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (null == this.currTransaction)
            this.currTransaction = this.fragmentManager.beginTransaction()

        val itemId = this.getItemId(position)
        val name = makeFragmentName(container.id, itemId)
        if (null == this.fragmentManager.findFragmentByTag(name))
            this.currTransaction?.detach(`object` as Fragment)
        else
            this.lazyItems.remove(position)
    }

    override fun addLazyItem(container: ViewGroup, position: Int): Fragment? {
        val fragment = lazyItems.get(position) ?: return null

        val itemId = this.getItemId(position)
        val name = makeFragmentName(container.id, itemId)
        if (null == this.fragmentManager.findFragmentByTag(name)) {
            if (null == this.currTransaction)
                this.currTransaction = this.fragmentManager.beginTransaction()
            this.currTransaction!!.add(container.id, fragment, name)
            this.lazyItems.remove(position)
        }

        return fragment
    }

    override fun finishUpdate(container: ViewGroup) {
        if (null != this.currTransaction) {
            this.currTransaction!!.commitAllowingStateLoss()
            this.currTransaction = null
            this.fragmentManager.executePendingTransactions()
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (`object` as Fragment).view === view

    fun getItemId(position: Int): Long = position.toLong()

    /**
     * Mark the fragment can be added lazily.
     */
    interface Laziable
}