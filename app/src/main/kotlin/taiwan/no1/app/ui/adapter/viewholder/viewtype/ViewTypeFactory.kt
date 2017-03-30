package taiwan.no1.app.ui.adapter.viewholder.viewtype

import android.view.View
import taiwan.no1.app.R
import taiwan.no1.app.mvp.models.CreditsInFilmModel
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmVideoModel
import taiwan.no1.app.mvp.models.cast.CastBriefModel
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.mvp.models.tv.TvEpisodesModel
import taiwan.no1.app.mvp.models.tv.TvSeasonsModel
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
        TV_LIST(R.layout.item_brief_tv),
        CAST_LIST(R.layout.item_brief_cast),
        CAST(R.layout.item_movie_casts_crews),
        CAST_RELATED(R.layout.item_movie_casts_crews),
        CREW(R.layout.item_movie_casts_crews),
        RELATED(R.layout.item_movie_casts_crews),
        VIDEO(R.layout.item_movie_trailers),
        TV_SEASON(R.layout.item_tv_seasons),
        EPISODE(R.layout.item_brief_episode),
    }

    override fun type(movieBriefModel: MovieBriefModel, isMain: Boolean): Int =
            if (isMain) TypeResource.MOVIE_LIST.ordinal else TypeResource.RELATED.ordinal

    override fun type(castBean: FilmCastsModel.CastBean): Int = TypeResource.CAST.ordinal

    override fun type(crewBean: FilmCastsModel.CrewBean): Int = TypeResource.CREW.ordinal

    override fun type(castBean: CreditsInFilmModel.CastInFilmBean): Int = TypeResource.CAST_RELATED.ordinal

    override fun type(crewBean: CreditsInFilmModel.CrewInFilmBean): Int = TypeResource.CREW.ordinal

    override fun type(movieVideosModel: FilmVideoModel): Int = TypeResource.VIDEO.ordinal

    override fun type(tvBriefModel: TvBriefModel, isMain: Boolean): Int =
            if (isMain) TypeResource.TV_LIST.ordinal else TypeResource.RELATED.ordinal

    override fun type(tvSeasonsModel: TvSeasonsModel): Int = TypeResource.TV_SEASON.ordinal

    override fun type(castBriefModel: CastBriefModel): Int = TypeResource.CAST_LIST.ordinal

    override fun type(tvEpisodesModel: TvEpisodesModel): Int = TypeResource.EPISODE.ordinal

    override fun createViewHolder(type: Int, itemView: View): BaseViewHolder<*> = when (type) {
        TypeResource.MOVIE_LIST.ordinal -> MovieListViewHolder(itemView)
        TypeResource.CAST.ordinal -> MovieCastViewHolder(itemView)
        TypeResource.CREW.ordinal -> MovieCrewViewHolder(itemView)
        TypeResource.RELATED.ordinal -> MovieRelatedViewHolder(itemView)
        TypeResource.VIDEO.ordinal -> MovieTrailerViewHolder(itemView)
        TypeResource.TV_LIST.ordinal -> TvListViewHolder(itemView)
        TypeResource.CAST_RELATED.ordinal -> MovieCastRelatedViewHolder(itemView)
        TypeResource.CAST_LIST.ordinal -> CastListViewHolder(itemView)
        TypeResource.TV_SEASON.ordinal -> TvSeasonViewHolder(itemView)
        TypeResource.EPISODE.ordinal -> TvSeasonViewHolder(itemView)
        else -> throw error("Illegal type")
    }
}
