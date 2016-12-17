package taiwan.no1.accounting.ui

import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle.components.support.RxFragment
import taiwan.no1.accounting.internal.di.HasComponent

/**
 * Base fragment for collecting common methods here.
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/5/16
 */

abstract class BaseFragment: RxFragment() {
    protected var rootView: View? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.init()
    }

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