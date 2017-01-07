package taiwan.no1.app.internal.di.components

import android.content.Context
import dagger.Component
import taiwan.no1.app.App
import taiwan.no1.app.domain.executor.PostExecutionThread
import taiwan.no1.app.domain.executor.ThreadExecutor
import taiwan.no1.app.domain.repository.IRepository
import taiwan.no1.app.internal.di.modules.AppModule
import taiwan.no1.app.internal.di.modules.NetModule
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 *
 * @author  Jieyi
 * @since   12/6/16
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    object Initializer {
        fun init(app: App): AppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .netModule(NetModule(app))
                .build()
    }

    // Exposed to sub-graphs.
    fun context(): Context

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun repository(): IRepository
}