package taiwan.no1.accounting.mvp.views

import com.trello.rxlifecycle.android.FragmentEvent
import rx.Observable

/**
 * This specifies [IFragmentView].
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/9/16
 */

interface IFragmentView {
    /**
     * Get a fragment life cycle.
     */
    fun getLifecycle(): Observable<FragmentEvent>?
}
