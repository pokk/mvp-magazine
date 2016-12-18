package taiwan.no1.accounting.ui

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to navigate between activities through the application.
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@Singleton
class Navigator @Inject constructor() {
    companion object {
        var ACTIVITY_RESOURCE_ID: String = "activity_id"
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateTo(context: Context) {
    }
}
