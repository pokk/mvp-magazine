package taiwan.no1.app.utilies

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import taiwan.no1.app.domain.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a [Scheduler] which will execute actions on the
 * Android UI thread.
 *
 * @author  Jieyi
 * @since   2016/12/06
 */

@Singleton
class UIThread @Inject constructor(): PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}