package taiwan.no1.app.ui.adapter.viewholder.viewtype

import android.view.View
import taiwan.no1.app.mvp.models.CreditsModel
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.mvp.models.MovieVideosModel
import taiwan.no1.app.ui.adapter.viewholder.BaseViewHolder

/**
 * A factory interface of the view type.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

interface IViewTypeFactory {
    fun type(movieBriefModel: MovieBriefModel, isMain: Boolean = true): Int

    fun type(castBean: MovieCastsModel.CastBean): Int

    fun type(crewBean: MovieCastsModel.CrewBean): Int

    fun type(castBean: CreditsModel.CastBean): Int

    fun type(crewBean: CreditsModel.CrewBean): Int

    fun type(movieVideosModel: MovieVideosModel): Int

    /**
     * Creating a view holder.
     *
     * @param type a res ID of layout.
     * @param itemView a view after inflating.
     * @return [BaseViewHolder] of recycler view holder.
     */
    fun createViewHolder(type: Int, itemView: View): BaseViewHolder
}