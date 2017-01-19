package taiwan.no1.app.internal.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import taiwan.no1.app.utilies.ImageLoader.ImageLoaderFactory

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/19
 */

@Module
class UtilsModule {
    @Provides
    @PerFragment
    fun provideGlideImageLoader(context: Context): IImageLoader =
            ImageLoaderFactory.getRequest(context, ImageLoaderFactory.Type.GLIDE)
}