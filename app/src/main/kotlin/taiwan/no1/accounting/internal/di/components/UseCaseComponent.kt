package taiwan.no1.accounting.internal.di.components

import android.support.v7.app.AppCompatActivity
import dagger.Component
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.internal.di.modules.ActivityModule
import taiwan.no1.accounting.internal.di.modules.UseCaseModule
import taiwan.no1.accounting.ui.fragments.FirstFragment

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class, UseCaseModule::class))
interface UseCaseComponent: ActivityComponent {
    object Initializer {
        fun init(appComponent: AppComponent,
                 activity: AppCompatActivity,
                 obj: Any? = null): UseCaseComponent = DaggerUseCaseComponent.builder()
                .appComponent(appComponent)
                .useCaseModule(if (null == obj) UseCaseModule() else UseCaseModule(obj as String))
                .activityModule(ActivityModule(activity))
                .build()
    }

    /**
     * After injected a fragment, the presenter of the fragment should be provided in [ActivityModule].
     */

    fun inject(firstFragment: FirstFragment)
}