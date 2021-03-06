package taiwan.no1.app.internal.di.components

import dagger.Component
import taiwan.no1.app.App
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.internal.di.modules.NetModule
import javax.inject.Singleton

/**
 *
 * @author  Jieyi
 * @since   12/8/16
 */

@Singleton
@Component(modules = arrayOf(NetModule::class))
interface NetComponent {
    object Initializer {
        @JvmStatic fun init(): NetComponent = DaggerNetComponent.builder()
                .netModule(NetModule(App.getAppContext()))
                .build()
    }

    fun inject(cloudDataStore: CloudDataStore)
}
