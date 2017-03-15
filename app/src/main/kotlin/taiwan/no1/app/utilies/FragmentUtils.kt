package taiwan.no1.app.utilies

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import dagger.internal.Preconditions
import java.util.*

/**
 * @author  jieyi
 * @since   2/15/17
 */

object FragmentUtils {
    /**
     * Adds a [Fragment] to this manager's layout.
     *
     * @param manager A [FragmentManager].
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     * @param needBack Set that it can back to previous fragment.
     * @param sharedElements Shared element objects and ids from layout xml [android:transitionName].
     * @return the identifier of this transaction's back stack entry.
     */
    fun addFragment(manager: FragmentManager,
                    containerViewId: Int,
                    fragment: Fragment,
                    needBack: Boolean = false,
                    sharedElements: HashMap<View, String>? = null): Int {
        Preconditions.checkNotNull(containerViewId)
        Preconditions.checkNotNull(fragment)
        Preconditions.checkNotNull(needBack)

        return manager.beginTransaction().apply {
            this.replace(containerViewId, fragment, fragment.javaClass.name)
            sharedElements?.forEach { this.addSharedElement(it.key, it.value) }
            // https://developer.android.com/training/implementing-navigation/temporal.html#back-fragments
            if (needBack)
                this.addToBackStack(fragment.javaClass.name)
        }.commit()
    }

    /**
     * Get a fragment from [FragmentManager] by the tag.
     *
     * @param manager A [FragmentManager].
     * @param tag From [Fragment]'s tag.
     * @return [Fragment] or null
     */
    fun findFragmentByTag(manager: FragmentManager, tag: String): Fragment? = manager.findFragmentByTag(tag)

    /**
     * Pop a [Fragment] from the [FragmentManager].
     *
     * @param manager A [FragmentManager].
     * @return is success to pop a [Fragment].
     */
    fun popFragment(manager: FragmentManager): Boolean = if (0 < manager.backStackEntryCount) {
        manager.popBackStackImmediate()
        true
    }
    else
        false

    /**
     * Clear all [Fragment] in the stack.
     *
     * @param manager A [FragmentManager].
     */
    fun popAllFragment(manager: FragmentManager) {
        (0..manager.backStackEntryCount - 1).forEach { FragmentUtils.popFragment(manager) }
    }

    fun removeFragment(manager: FragmentManager, fragment: Fragment) {
        manager.beginTransaction().remove(fragment).commitNow()
    }

    fun removeRecursiveFragment(manager: FragmentManager) {
        manager.fragments?.forEach {
            it?.let {
                it.childFragmentManager?.let { removeRecursiveFragment(it) }
                FragmentUtils.removeFragment(manager, it)
            }
        }
    }
}
