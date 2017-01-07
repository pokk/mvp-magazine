package taiwan.no1.app.mvp.views

import android.content.Context

/**
 * Interface representing a View that will use to load data.
 *
 * @author  Jieyi
 * @since   12/6/16
 */

interface IView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    fun showLoading()

    /**
     * Hide a loading view.
     */
    fun hideLoading()

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    fun showRetry()

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    fun hideRetry()

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    fun showError(message: String)

    /**
     * Get the application [Context].
     */
    fun context(): Context
}
