package com.playone.mobile.presenter

import com.playone.mobile.view.IView

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

interface IPresenter<in V: IView> {
    /**
     * Set a viewable.
     *
     * @param view [IView]
     */
    fun setView(view: V)

    /**
     * Initial method.
     */
    fun init()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    fun resume()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    fun pause()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    fun destroy()
}