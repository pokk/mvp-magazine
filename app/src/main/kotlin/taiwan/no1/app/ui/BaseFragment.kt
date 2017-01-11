package taiwan.no1.app.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.intrusoft.squint.DiagonalView
import com.trello.rxlifecycle.android.FragmentEvent
import com.trello.rxlifecycle.components.support.RxFragment
import dagger.internal.Preconditions
import rx.Observable
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.views.IFragmentView
import taiwan.no1.app.mvp.views.IView

/**
 * Base presenter for collecting common methods here.
 *
 * @author  Jieyi
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

    override final fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.init(savedInstanceState)
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

    //region Initialization's order
    /**
     * Initialize the fragment method.
     *
     * @param savedInstanceState before status.
     */
    abstract protected fun init(savedInstanceState: Bundle?)

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
     * Initialize the presenter in [onCreateView].
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

    /**
     * A listener for clearing the [DiagonalView]'s foreground color after loading the img from http uri.
     *
     * @param dvBackgroundPoster [DiagonalView]'s image.
     * @return [RequestListener].
     */
    protected fun clearDiagonalViewListener(dvBackgroundPoster: DiagonalView): RequestListener<String, GlideDrawable> =
            object: RequestListener<String, GlideDrawable> {
                override fun onException(e: Exception,
                                         model: String,
                                         target: Target<GlideDrawable>,
                                         isFirstResource: Boolean): Boolean = false

                override fun onResourceReady(resource: GlideDrawable,
                                             model: String,
                                             target: Target<GlideDrawable>,
                                             isFromMemoryCache: Boolean,
                                             isFirstResource: Boolean): Boolean = let {
                    dvBackgroundPoster.solidColor = Color.TRANSPARENT
                    false
                }
            }
    //endregion
}
