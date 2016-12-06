package taiwan.no1.accounting.internal.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.data.AccountDataRepository
import taiwan.no1.accounting.data.AccountRepository
import taiwan.no1.accounting.data.executor.JobExecutor
import taiwan.no1.accounting.data.source.CloudDataStore
import taiwan.no1.accounting.data.source.DataStore
import taiwan.no1.accounting.domain.executor.PostExecutionThread
import taiwan.no1.accounting.domain.executor.ThreadExecutor
import taiwan.no1.accounting.utilies.UIThread
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

    @Provides @Singleton
    fun providePlayoneDataStore(): DataStore {
        return CloudDataStore()
    }

    @Provides
    @Singleton
    fun providePlayoneRepository(accountDataRepository: AccountDataRepository): AccountRepository = accountDataRepository

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor
    
//    @Provides
//    @Singleton
//    fun createFake(threadExecutor: ThreadExecutor,
//                   postExecutionThread: PostExecutionThread,
//                   accountDataRepository: AccountRepository): CreateFakeCase = CreateFakeCase(threadExecutor, 
//            postExecutionThread,
//            accountDataRepository)
}