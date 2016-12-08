package taiwan.no1.accounting.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
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

open class BaseActivity: AppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        this.initialInjector()
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
    fun addFragment(containerViewId: Int, fragment: Fragment, needBack: Boolean, sharedElement: View?, shareElementName: String?) {
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
    fun popFragment(): Unit {
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
