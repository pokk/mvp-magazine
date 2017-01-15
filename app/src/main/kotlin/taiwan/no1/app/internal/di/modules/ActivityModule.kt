package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.mvp.contracts.VideoContract
import taiwan.no1.app.mvp.presenters.MainPresenter
import taiwan.no1.app.mvp.presenters.VideoPresenter
import taiwan.no1.app.ui.Navigator

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * @author  Jieyi
 * @since   5/29/16
 */

@Module
class ActivityModule {
    @Provides
    @PerActivity
    fun provideNavigator(): Navigator = Navigator()

    @Provides
    @PerActivity
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()

    @Provides
    @PerActivity
    fun provideVideoPresenter(): VideoContract.Presenter = VideoPresenter()
}