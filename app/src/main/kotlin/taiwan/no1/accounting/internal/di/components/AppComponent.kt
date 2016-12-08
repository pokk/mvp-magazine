package taiwan.no1.accounting.internal.di.components

import android.content.Context
import dagger.Component
import taiwan.no1.accounting.App
import taiwan.no1.accounting.domain.executor.PostExecutionThread
import taiwan.no1.accounting.domain.executor.ThreadExecutor
import taiwan.no1.accounting.domain.repository.AccountRepository
import taiwan.no1.accounting.internal.di.modules.AppModule
import taiwan.no1.accounting.internal.di.modules.NetModule
import taiwan.no1.accounting.ui.BaseActivity
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    object Initializer {
        fun init(app: App, baseUrl: String = ""): AppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .netModule(NetModule(baseUrl))
                .build()
    }

    fun inject(baseActivity: BaseActivity)

    // Exposed to sub-graphs.
    fun context(): Context

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun accountRepository(): AccountRepository
}