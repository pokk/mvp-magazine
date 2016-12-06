package taiwan.no1.accounting.internal.di.components

import android.content.Context
import dagger.Component
import taiwan.no1.accounting.App
import taiwan.no1.accounting.data.AccountRepository
import taiwan.no1.accounting.domain.executor.PostExecutionThread
import taiwan.no1.accounting.domain.executor.ThreadExecutor
import taiwan.no1.accounting.internal.di.modules.AppModule
import taiwan.no1.accounting.internal.di.modules.NetModule
import taiwan.no1.accounting.ui.activities.BaseActivity
import javax.inject.Singleton

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
    
    object Initializer {
        fun init(app: App): AppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .netModule(NetModule("xxx"))
                .build()
    }

    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun accountRepository(): AccountRepository
}