package taiwan.no1.accounting.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle.android.FragmentEvent
import com.trello.rxlifecycle.components.support.RxFragment
import dagger.internal.Preconditions
import rx.Observable
import taiwan.no1.accounting.internal.di.HasComponent
import taiwan.no1.accounting.mvp.views.IFragmentView
import taiwan.no1.accounting.mvp.views.IView

/**
 * Base presenter for collecting common methods here.
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/5/16
 */

abstract class BaseFragment: RxFragment(), IView, IFragmentView {
    protected var rootView: View? = null

    //region Fragment lifecycle.
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.init()
    }
    //endregion

    //region Presenter implements
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showRetry() {
    }

    override fun hideRetry() {
    }

    override fun showError(message: String) {
        Preconditions.checkNotNull(message)
    }

    /**
     * Get the application [Context].
     *
     * @return application [Context].
     */
    override fun context(): Context = this.activity.applicationContext

    /**
     * Get this fragment lifecycle.
     *
     * @return [Observable] of lifecycle.
     */
    override fun getLifecycle(): Observable<FragmentEvent> = this.lifecycle()
    //endregion

    /**
     * Get a use case component from a owner activity.
     *
     * @param componentType owner [BaseActivity] class name.
     * @param obj a use case parameter.
     * @return [UseCaseComponent].
     */
    protected fun <C> getComponent(componentType: Class<C>, obj: Any?): C {
        return componentType.cast((activity as HasComponent<*>).getComponent(obj))
    }

    /**
     * Initialize method.
     */
    abstract protected fun init()
}
