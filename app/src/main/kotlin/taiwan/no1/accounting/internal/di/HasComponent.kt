package taiwan.no1.accounting.internal.di

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 * 
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   2016/12/06
 */

interface HasComponent<out C> {
    fun getFragmentComponent(obj: Any?): C
}