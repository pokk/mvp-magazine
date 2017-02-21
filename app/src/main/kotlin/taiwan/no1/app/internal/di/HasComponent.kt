package taiwan.no1.app.internal.di

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 *
 * @author  Jieyi
 * @since   12/6/16
 */

interface HasComponent<out C> {
    fun getFragmentComponent(): C
}