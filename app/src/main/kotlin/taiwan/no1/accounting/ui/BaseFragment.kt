package taiwan.no1.accounting.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle.android.FragmentEvent
import com.trello.rxlifecycle.components.support.RxFragment
import dagger.internal.Preconditions
import rx.Observable
import taiwan.no1.accounting.internal.di.HasComponent
import taiwan.no1.accounting.internal.di.components.FragmentComponent
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
    override final fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                    savedInstanceState: Bundle?): View? {
        // Avoid that an activity is deleted and get null pointer so inject the component here.
        this.inject()
        // Keep the instance data.
        this.retainInstance = true

        // FIXED: https://www.zybuluo.com/kimo/note/255244
        if (null == rootView)
            rootView = inflater.inflate(this.inflateView(), null)
        val parent: ViewGroup? = rootView?.parent as ViewGroup?
        parent?.removeView(rootView)

        // Init the presenter.
        this.initPresenter()

        return rootView
    }

    override final fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
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
     * Get the [Context] of this application.
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
     * Initialize the fragment method.
     */
    abstract protected fun init()

    /**
     * Injected the presenter and the fragment use case.
     */
    abstract protected fun inject()

    /**
     * Set the view for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    @LayoutRes
    abstract protected fun inflateView(): Int

    /**
     * Initialize the presenter.
     */
    abstract protected fun initPresenter()

    /**
     * Get a use case component from a owner activity.
     *
     * @param componentType owner [BaseActivity] class name.
     * @param obj a use case parameter.
     * @return [FragmentComponent].
     */
    protected fun <C> getComponent(componentType: Class<C>, obj: Any?): C =
            componentType.cast((activity as HasComponent<*>).getFragmentComponent(obj))
}
