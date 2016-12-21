package taiwan.no1.accounting.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.touchin.constant.RxbusTag
import com.trello.rxlifecycle.android.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import dagger.internal.Preconditions
import rx.Observable
import taiwan.no1.accounting.App
import taiwan.no1.accounting.internal.di.components.ActivityComponent
import taiwan.no1.accounting.internal.di.components.AppComponent
import taiwan.no1.accounting.internal.di.components.FragmentComponent
import taiwan.no1.accounting.mvp.views.IActivityView
import taiwan.no1.accounting.mvp.views.IView
import taiwan.no1.accounting.utilies.AppLog
import javax.inject.Inject

/**
 * Base activity for collecting all common methods here.
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/5/16
 */

abstract class BaseActivity: RxAppCompatActivity(), IView, IActivityView {
    @Inject
    lateinit var navigator: Navigator

    // Register it in the parent class that it will be not reflected.
    protected var busEvent = object {
        @Subscribe(tags = arrayOf(Tag(RxbusTag.NAVIGATOR)))
        fun test(test: String) {
            AppLog.d(test)
        }
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getComponent().inject(this)

        this.initialInjector()
        this.init(savedInstanceState)

        // Register RxBus.
        RxBus.get().register(this.busEvent)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister RxBus.
        RxBus.get().unregister(this.busEvent)
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
    override fun context(): Context = this.applicationContext

    /**
     * Get this activity lifecycle.
     *
     * @return [Observable] of lifecycle.
     */
    override fun getLifecycle(): Observable<ActivityEvent> = this.lifecycle()
    //endregion

    /**
     * Initialize method.
     */
    abstract protected fun init(savedInstanceState: Bundle?)

    protected fun getComponent(): ActivityComponent =
            ActivityComponent.Initializer.init(this.getApplicationComponent())

    protected fun provideFragmentComponent(obj: Any?): FragmentComponent =
            FragmentComponent.Initializer.init(this.getApplicationComponent())

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return [AppComponent]
     */
    protected fun getApplicationComponent(): AppComponent = App.appComponent()

    /**
     * Get an injector and inject [BaseActivity].
     */
    protected fun initialInjector() {
//        this.getApplicationComponent().inject(BaseActivity@ this)
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected fun addFragment(containerViewId: Int,
                              fragment: Fragment,
                              needBack: Boolean,
                              sharedElement: View?,
                              shareElementName: String?) {
        Preconditions.checkNotNull(containerViewId)
        Preconditions.checkNotNull(fragment)
        Preconditions.checkNotNull(needBack)

        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment, fragment.javaClass.name)

        if (null != sharedElement && null != shareElementName) {
            fragmentTransaction.addSharedElement(sharedElement, shareElementName)
        }

        // https://developer.android.com/training/implementing-navigation/temporal.html#back-fragments
        if (needBack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }

        fragmentTransaction.commit()
    }

    /**
     * Get a fragment from queue by the tag.
     *
     * @param tag [Fragment]'s tag.
     * @return [Fragment]
     */
    protected fun findFragmentByTag(tag: String): Fragment {
        return this.supportFragmentManager.findFragmentByTag(tag)
    }

    /**
     * Pop a [Fragment] from [getSupportFragmentManager].
     */
    protected fun popFragment() {
        this.supportFragmentManager.popBackStack()
    }
}
