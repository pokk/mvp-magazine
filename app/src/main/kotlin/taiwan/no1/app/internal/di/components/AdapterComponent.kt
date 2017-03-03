package taiwan.no1.app.internal.di.components

import dagger.Component
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.modules.AdapterModule
import taiwan.no1.app.internal.di.modules.UtilsModule
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.viewholder.*

/**
 *
 * @author  Jieyi
 * @since   2/21/17
 */

@PerFragment
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(AdapterModule::class, UtilsModule::class))
interface AdapterComponent {
    object Initializer {
        fun init(appComponent: AppComponent): AdapterComponent = DaggerAdapterComponent.builder()
                .appComponent(appComponent)
                .adapterModule(AdapterModule())
                .utilsModule(UtilsModule())
                .build()
    }

    fun inject(commonRecyclerAdapter: CommonRecyclerAdapter)

    fun inject(castListViewHolder: CastListViewHolder)

    fun inject(movieCastRelatedViewHolder: MovieCastRelatedViewHolder)

    fun inject(movieCastViewHolder: MovieCastViewHolder)

    fun inject(movieCrewViewHolder: MovieCrewViewHolder)

    fun inject(movieListViewHolder: MovieListViewHolder)

    fun inject(movieRelatedViewHolder: MovieRelatedViewHolder)

    fun inject(movieTrailerViewHolder: MovieTrailerViewHolder)

    fun inject(tvListViewHolder: TvListViewHolder)

    fun inject(tvSeasonViewHolder: TvSeasonViewHolder)
}