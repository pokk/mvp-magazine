package taiwan.no1.app.ui.adapter.viewholder.viewtype

import android.view.View
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.CreditsModel
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.mvp.models.MovieCastsModel
import taiwan.no1.app.mvp.models.MovieVideosModel
import taiwan.no1.app.ui.adapter.viewholder.*

/**
 * Implement the [IViewTypeFactory], we design that getting a view type is equal get a layout id. The reason
 * is convenient to set the view.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class ViewTypeFactory: IViewTypeFactory {
    enum class TypeResource(val id: Int) {
        MOVIE_LIST(R.layout.item_brief_movie),
        MOVIE_CAST(R.layout.item_movie_casts_crews),
        MOVIE_CREW(R.layout.item_movie_casts_crews),
        MOVIE_RELATED(R.layout.item_movie_casts_crews),
        MOVIE_VIDEO(R.layout.item_movie_trailers),
        CAST(R.layout.item_movie_casts_crews),
        CREW(R.layout.item_movie_casts_crews),
    }

    override fun type(movieBriefModel: MovieBriefModel, isMain: Boolean): Int =
            if (isMain) TypeResource.MOVIE_LIST.ordinal else TypeResource.MOVIE_RELATED.ordinal

    override fun type(castBean: MovieCastsModel.CastBean): Int = TypeResource.MOVIE_CAST.ordinal

    override fun type(crewBean: MovieCastsModel.CrewBean): Int = TypeResource.MOVIE_CREW.ordinal

    override fun type(castBean: CreditsModel.CastBean): Int = TypeResource.CAST.ordinal

    override fun type(crewBean: CreditsModel.CrewBean): Int = TypeResource.CREW.ordinal

    override fun type(movieVideosModel: MovieVideosModel): Int = TypeResource.MOVIE_VIDEO.ordinal

    override fun createViewHolder(type: Int, itemView: View): BaseViewHolder = when (type) {
        TypeResource.MOVIE_LIST.ordinal -> MovieListViewHolder(itemView)
        TypeResource.MOVIE_CAST.ordinal -> MovieCastViewHolder(itemView)
        TypeResource.MOVIE_CREW.ordinal -> MovieCrewViewHolder(itemView)
        TypeResource.MOVIE_RELATED.ordinal -> MovieRelatedViewHolder(itemView)
        TypeResource.MOVIE_VIDEO.ordinal -> MovieTrailerViewHolder(itemView)
        TypeResource.CAST.ordinal -> MovieCastRelatedViewHolder(itemView)
        else -> throw error("Illegal type")
    }
}