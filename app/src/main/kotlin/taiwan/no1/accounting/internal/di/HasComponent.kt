package taiwan.no1.accounting.internal.di

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   2016/12/06
 */

interface HasComponent<out C> {
    fun getComponent(obj: Any?): C
}