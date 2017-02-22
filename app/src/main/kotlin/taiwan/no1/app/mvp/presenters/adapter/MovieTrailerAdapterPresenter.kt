package taiwan.no1.app.mvp.presenters.adapter

import taiwan.no1.app.mvp.contracts.adapter.TrailerAdapterContract.Presenter
import taiwan.no1.app.mvp.contracts.adapter.TrailerAdapterContract.View
import taiwan.no1.app.mvp.models.FilmVideoModel

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

class MovieTrailerAdapterPresenter: BaseAdapterPresenter<View, FilmVideoModel>(), Presenter 