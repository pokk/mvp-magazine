package taiwan.no1.app.ui.fragments

import android.support.v4.app.Fragment

/**
 * For main fragments' controller.
 *
 * @author  Jieyi
 * @since   2/3/17
 */

interface IMainFragment {
    /**
     * Get the [Fragment] which is displaying now.
     *
     * @return current display [Fragment].
     */
    fun getCurrentDisplayFragment(): Fragment?
}