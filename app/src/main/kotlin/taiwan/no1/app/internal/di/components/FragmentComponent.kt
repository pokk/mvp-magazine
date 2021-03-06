package taiwan.no1.app.internal.di.components

import dagger.Component
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.modules.FragmentModule
import taiwan.no1.app.internal.di.modules.FragmentUseCaseModule
import taiwan.no1.app.internal.di.modules.UtilsModule
import taiwan.no1.app.ui.fragments.*

/**
 *
 * @author  Jieyi
 * @since   12/20/16
 */

@PerFragment
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(FragmentModule::class, FragmentUseCaseModule::class, UtilsModule::class))
interface FragmentComponent {
    object Initializer {
        fun init(appComponent: AppComponent): FragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentUseCaseModule(FragmentUseCaseModule())
                .fragmentModule(FragmentModule())
                .utilsModule(UtilsModule())
                .build()
    }

    /**
     * After injected a fragment, the presenter of the fragment should be provided in [FragmentModule].
     */

    fun inject(movieListFragment: MovieListFragment)

    fun inject(movieDetailFragment: MovieDetailFragment)

    fun inject(movieGalleryFragment: MovieGalleryFragment)

    fun inject(castDetailFragment: CastDetailFragment)

    fun inject(tvListFragment: TvListFragment)

    fun inject(tvDetailFragment: TvDetailFragment)

    fun inject(tvSeasonFragment: TvSeasonFragment)

    fun inject(tvEpisodeFragment: TvEpisodeFragment)

    fun inject(actressMainFragment: ActressMainFragment)
}