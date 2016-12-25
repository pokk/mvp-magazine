package taiwan.no1.app.internal.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import taiwan.no1.app.data.executor.JobExecutor
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.domain.executor.PostExecutionThread
import taiwan.no1.app.domain.executor.ThreadExecutor
import taiwan.no1.app.domain.repository.IRepository
import taiwan.no1.app.utilies.UIThread
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
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
    fun provideSharePreferences(application: Application): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @Singleton
    fun provideRepository(dataRepository: DataRepository): IRepository =
            dataRepository

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor
}
