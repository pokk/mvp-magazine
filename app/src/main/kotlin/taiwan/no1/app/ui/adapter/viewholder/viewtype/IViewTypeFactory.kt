package taiwan.no1.app.ui.adapter.viewholder.viewtype

import android.view.View
import taiwan.no1.app.mvp.models.*
import taiwan.no1.app.ui.adapter.viewholder.BaseViewHolder

/**
 * A factory interface of the view type.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

interface IViewTypeFactory {
    fun type(movieBriefModel: MovieBriefModel, isMain: Boolean = true): Int

    fun type(castBean: FilmCastsModel.CastBean): Int

    fun type(crewBean: FilmCastsModel.CrewBean): Int

    fun type(castBean: CreditsInFilmModel.CastInFilmBean): Int

    fun type(crewBean: CreditsInFilmModel.CrewInFilmBean): Int

    fun type(movieVideosModel: FilmVideoModel): Int

    fun type(tvBriefModel: TvBriefModel): Int

    fun type(castBriefModel: CastBriefModel): Int

    /**
     * Creating a view holder.
     *
     * @param type a res ID of layout.
     * @param itemView a view after inflating.
     * @return [BaseViewHolder] of recycler view holder.
     */
    fun createViewHolder(type: Int, itemView: View): BaseViewHolder
}