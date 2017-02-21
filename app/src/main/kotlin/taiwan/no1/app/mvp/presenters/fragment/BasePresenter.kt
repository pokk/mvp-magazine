package taiwan.no1.app.mvp.presenters.fragment

import android.support.annotation.CallSuper
import dagger.internal.Preconditions
import taiwan.no1.app.mvp.presenters.IPresenter
import taiwan.no1.app.mvp.views.IView

/**
 * Base fragment for collecting common methods here.
 *
 * @author  Jieyi
 * @since   12/20/16
 */

open class BasePresenter<V: IView>: IPresenter<V> {
    lateinit protected var view: V

    @CallSuper
    override fun init(view: V) {
        Preconditions.checkNotNull(view)

        this.view = view
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
}