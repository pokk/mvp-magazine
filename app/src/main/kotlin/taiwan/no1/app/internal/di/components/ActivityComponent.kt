package taiwan.no1.app.internal.di.components

import dagger.Component
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.modules.ActivityModule
import taiwan.no1.app.internal.di.modules.ActivityUseCaseModule
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.activities.MainActivity

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class)
        , modules = arrayOf(ActivityModule::class, ActivityUseCaseModule::class))
interface ActivityComponent {
    object Initializer {
        fun init(appComponent: AppComponent): ActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityUseCaseModule(ActivityUseCaseModule())
                .activityModule(ActivityModule())
                .build()
    }

    /**
     * After injected an activity, the presenter of the activity should be provided in [ActivityModule].
     */

    // This injection is for the activity navigator.
    fun inject(baseActivity: BaseActivity)
    
    fun inject(mainActivity: MainActivity)
}
