package taiwan.no1.accounting

import android.app.Application
import android.content.Context
import taiwan.no1.accounting.internal.di.components.AppComponent

/**
 * Android Main Application
 * 
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

class App: Application() {
    companion object {
        @JvmStatic fun appComponent(context: Context): AppComponent =
                (context as App).appComponent
    }

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this) }
}