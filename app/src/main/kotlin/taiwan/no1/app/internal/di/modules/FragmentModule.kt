package taiwan.no1.app.internal.di.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.app.domain.CreateFakeUseCase
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.FirstContract
import taiwan.no1.app.mvp.presenters.FirstPresenter

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
    fun provideFirstPresenter(fakeUseCase: CreateFakeUseCase): FirstContract.Presenter =
            FirstPresenter(fakeUseCase)
}