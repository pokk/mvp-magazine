package taiwan.no1.accounting.internal.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import taiwan.no1.accounting.api.RestfulApiFactory
import taiwan.no1.accounting.api.config.IApiConfig
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/06
 */

@Module
class NetModule {
    @Provides
    @Singleton
    fun provideConverterGson(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson = with(GsonBuilder()) {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        create()
    }

//    @Provides
//    @Singleton
//    fun provideOkHttpCache(app: Application): Cache = Cache(app.cacheDir, 10 * 1024 * 1024 /* 10 MiB */)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideBaseRetrofitBuilder(converter: GsonConverterFactory,
                                   callAdapter: RxJavaCallAdapterFactory,
                                   okHttpClient: OkHttpClient): Retrofit.Builder =
            Retrofit.Builder().apply {
                addConverterFactory(converter)
                addCallAdapterFactory(callAdapter)
                client(okHttpClient)
            }

    @Provides
    @Singleton
    @Named("FakeHttp")
    fun provideRetrofit2(baseBuilder: Retrofit.Builder, restfulApiFactory: RestfulApiFactory): Retrofit =
            with(baseBuilder) {
                val config: IApiConfig = restfulApiFactory.createFakeConfig()
                baseUrl(config.apiBaseUrl)
                build()
            }
}
