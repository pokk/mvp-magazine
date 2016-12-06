package taiwan.no1.accounting.internal.di.modules

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
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
    fun provideConverterGson(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
            create()

    @Provides
    @Singleton
    fun provideOkHttpCache(app: Application): Cache = Cache(app.cacheDir, 10 * 1024 * 1024 /* 10 MiB */)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder().cache(cache).build()

    @Provides
    @Singleton
    fun provideRetrofit2(converter: GsonConverterFactory,
                         callAdapter: RxJavaCallAdapterFactory,
                         okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().
            baseUrl(this.baseUrl).
            addConverterFactory(converter).
            addCallAdapterFactory(callAdapter).
            client(okHttpClient).
            build()
}
