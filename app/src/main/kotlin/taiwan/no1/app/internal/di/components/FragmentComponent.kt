package taiwan.no1.app.internal.di.components

import dagger.Component
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.modules.FragmentModule
import taiwan.no1.app.internal.di.modules.FragmentUseCaseModule
import taiwan.no1.app.ui.fragments.*

/**
 *
 * @author  Jieyi
 * @since   2016/12/20
 */

@PerFragment
@Component(dependencies = arrayOf(AppComponent::class)
        , modules = arrayOf(FragmentModule::class, FragmentUseCaseModule::class))
interface FragmentComponent {
    object Initializer {
        fun init(appComponent: AppComponent): FragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentUseCaseModule(FragmentUseCaseModule())
                .fragmentModule(FragmentModule())
                .build()
    }

    /**
     * After injected a fragment, the presenter of the fragment should be provided in [FragmentModule].
     */

    fun inject(movieListFragment: MovieListFragment)

    fun inject(movieDetailFragment: MovieDetailFragment)

    fun inject(movieGalleryFragment: MovieGalleryFragment)

    fun inject(castDetailFragment: CastDetailFragment)

    fun inject(tvListFragment: TVListFragment)
}