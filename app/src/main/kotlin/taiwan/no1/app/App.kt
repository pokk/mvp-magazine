package taiwan.no1.app

import android.app.Application
import android.content.Context
import com.antfortune.freeline.FreelineCore
import taiwan.no1.app.internal.di.components.AppComponent

/**
 * Android Main Application.
 *
 * @author  Jieyi
 * @since   12/6/16
 */

class App: Application() {
    companion object {
        lateinit private var context: Context

        @JvmStatic fun appComponent(): AppComponent = (context as App).appComponent
        // Provide the global application context.
        @JvmStatic fun getAppContext(): Context = context
    }

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(App@ this) }

    override fun onCreate() {
        super.onCreate()

        FreelineCore.init(this)
        context = this
    }
}