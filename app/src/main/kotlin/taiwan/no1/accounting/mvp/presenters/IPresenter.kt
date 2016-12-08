package taiwan.no1.accounting.mvp.presenters

import taiwan.no1.accounting.mvp.views.IView

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 * 
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
     * (Activity or Fragment) [Fragment.onResume] method.
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