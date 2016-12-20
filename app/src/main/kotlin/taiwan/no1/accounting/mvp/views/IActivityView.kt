package taiwan.no1.accounting.mvp.views

import com.trello.rxlifecycle.android.ActivityEvent
import rx.Observable

/**
 * This specifies [IActivityView]
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/9/16
 */

interface IActivityView {
    /**
     * Get a activity life cycle.
     */
    fun getLifecycle(): Observable<ActivityEvent>
}
