package taiwan.no1.accounting.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import taiwan.no1.accounting.internal.di.HasComponent

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/5/16
 */

abstract class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun <C> getComponent(componentType: Class<C>, obj: Any?): C {
        return componentType.cast((activity as HasComponent<C>).getComponent(obj))
    }
}