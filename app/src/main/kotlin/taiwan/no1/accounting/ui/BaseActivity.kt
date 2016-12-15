package taiwan.no1.accounting.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.view.View
import com.hwangjr.rxbus.RxBus
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import dagger.internal.Preconditions
import taiwan.no1.accounting.App
import taiwan.no1.accounting.internal.di.components.AppComponent
import taiwan.no1.accounting.internal.di.modules.ActivityModule
import javax.inject.Inject

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/5/16
 */

open class BaseActivity: RxAppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        this.initialInjector()

        // Register RxBus.
        RxBus.get().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister RxBus.
        RxBus.get().unregister(this)
    }

    fun initialInjector() {
        this.getApplicationComponent().inject(BaseActivity@ this)
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    fun addFragment(containerViewId: Int, fragment: Fragment, needBack: Boolean, sharedElement: View?,
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

    fun findFragmentByTag(tag: String): Fragment {
        return this.supportFragmentManager.findFragmentByTag(tag)
    }

    /**
     * Pop a [Fragment] from [getSupportFragmentManager].
     */
    fun popFragment() {
        this.supportFragmentManager.popBackStack()
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return [AppComponent]
     */
    protected fun getApplicationComponent(): AppComponent {
        return App.appComponent(application)
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return [ActivityModule]
     */
    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }
}
