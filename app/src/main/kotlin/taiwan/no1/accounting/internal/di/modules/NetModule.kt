package taiwan.no1.accounting.internal.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/06
 */

@Module
class NetModule(val baseUrl: String) {
    @Provides
    @Singleton
    fun provideRetrofit2(converter: GsonConverterFactory, callAdapter: RxJavaCallAdapterFactory): Retrofit = Retrofit.Builder().
            baseUrl(this.baseUrl).
            addConverterFactory(converter).
            addCallAdapterFactory(callAdapter).
            build()

    @Provides
    @Singleton
    fun provideConverterGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()
}