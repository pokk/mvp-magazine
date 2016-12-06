package taiwan.no1.accounting.internal.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   12/6/16
 */

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAppContext(): Context = app

    @Provides
    @Singleton
    fun provideSharePreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

//    @Provides
//    @Singleton
//    fun providePostExecutionThread(uiThread: UIThread) {
//        
//    }
}