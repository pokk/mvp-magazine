package com.playone.mobile.view

import android.content.Context
import com.playone.mobile.presenter.Presentable

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

interface Viewable<P: Presentable> {
    /**
     * Set a presenter.
     *
     * @param presenter [Presentable]
     */
    fun setPresenter(presenter: Presentable)

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
     * Get a [Context].
     */
    fun context(): Context
}
