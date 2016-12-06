package taiwan.no1.accounting.internal.di.components

import android.content.Context
import dagger.Component
import taiwan.no1.accounting.Application
import taiwan.no1.accounting.internal.di.modules.AppModule
import javax.inject.Singleton

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    object Initializer {
        fun init(app: Application): AppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
    }

    fun context(): Context
}