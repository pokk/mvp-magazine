package taiwan.no1.accounting.ui

import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@Singleton
class Navigator @Inject constructor() {
    companion object {
        @JvmStatic var FRAGMENT_RESOURCE_ID: String = "fragment_resource_id"
    }
}
