package taiwan.no1.accounting.mvp.presenters

import dagger.internal.Preconditions
import taiwan.no1.accounting.mvp.views.IView

/**
 * Base fragment for collecting common methods here.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/20
 */

open class BasePresenter<V: IView>: IPresenter<V> {
    lateinit protected var view: V

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