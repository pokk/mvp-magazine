package taiwan.no1.accounting.internal.di.components

import dagger.Component
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.internal.di.modules.ActivityModule
import taiwan.no1.accounting.internal.di.modules.UseCaseModule
import taiwan.no1.accounting.ui.fragments.MainFragment

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, UseCaseModule::class))
interface UseCaseComponent: ActivityComponent {
    fun inject(mainFragment: MainFragment)
}