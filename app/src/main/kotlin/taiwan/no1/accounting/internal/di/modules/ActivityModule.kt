package taiwan.no1.accounting.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.mvp.contracts.MainContract
import taiwan.no1.accounting.mvp.presenters.MainPresenter

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */

@Module
class ActivityModule {
    @Provides
    @PerActivity
    fun provideMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter = mainPresenter
}