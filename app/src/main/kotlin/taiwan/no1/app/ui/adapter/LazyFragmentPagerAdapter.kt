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

abstract class LazyFragmentPagerAdapter(private val mFragmentManager: FragmentManager): LazyPagerAdapter<Fragment>() {
    private var mCurTransaction: FragmentTransaction? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (mCurTransaction == null)
            mCurTransaction = mFragmentManager.beginTransaction()

        val itemId = getItemId(position)

        // Do we already have this fragment?
        val name = makeFragmentName(container.id, itemId)
        var fragment = mFragmentManager.findFragmentByTag(name)
        if (fragment != null)
            mCurTransaction!!.attach(fragment)
        else {
            fragment = getItem(container, position)
            if (fragment is Laziable)
                mLazyItems.put(position, fragment)
            else
                mCurTransaction!!.add(container.id, fragment, name)
        }
        if (fragment !== this.currentItem) {
            fragment.setMenuVisibility(false)
            fragment.userVisibleHint = false
        }

        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (mCurTransaction == null)
            mCurTransaction = mFragmentManager.beginTransaction()

        val itemId = getItemId(position)
        val name = makeFragmentName(container.id, itemId)
        if (mFragmentManager.findFragmentByTag(name) == null)
            mCurTransaction!!.detach(`object` as Fragment)
        else
            mLazyItems.remove(position)
    }

    override fun addLazyItem(container: ViewGroup, position: Int): Fragment? {
        val fragment = mLazyItems.get(position) ?: return null

        val itemId = getItemId(position)
        val name = makeFragmentName(container.id, itemId)
        if (mFragmentManager.findFragmentByTag(name) == null) {
            if (mCurTransaction == null)
                mCurTransaction = mFragmentManager.beginTransaction()
            mCurTransaction!!.add(container.id, fragment, name)
            mLazyItems.remove(position)
        }
        return fragment
    }

    override fun finishUpdate(container: ViewGroup) {
        if (mCurTransaction != null) {
            mCurTransaction!!.commitAllowingStateLoss()
            mCurTransaction = null
            mFragmentManager.executePendingTransactions()
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (`object` as Fragment).view === view

    fun getItemId(position: Int): Long = position.toLong()

    /**
     * mark the fragment can be added lazily
     */
    interface Laziable

    companion object {
        private val TAG = "LazyFragmentPagerAdapter"
        private val DEBUG = false

        private fun makeFragmentName(viewId: Int, id: Long): String {
            return "android:switcher:$viewId:$id"
        }
    }

}