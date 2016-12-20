package taiwan.no1.accounting.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.internal.di.annotations.PerFragment
import taiwan.no1.accounting.mvp.contracts.FirstContract
import taiwan.no1.accounting.mvp.presenters.FirstPresenter

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/20
 */

@Module
class FragmentModule {
    @Provides
    @PerFragment
    fun provideFirstPresenter(firstPresenter: FirstPresenter): FirstContract.Presenter = firstPresenter
}