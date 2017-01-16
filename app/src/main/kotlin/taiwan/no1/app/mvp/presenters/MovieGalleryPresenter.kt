package taiwan.no1.app.mvp.presenters

import android.graphics.Bitmap
import taiwan.no1.app.mvp.contracts.MovieGalleryContract

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */
class MovieGalleryPresenter: BasePresenter<MovieGalleryContract.View>(), MovieGalleryContract.Presenter {
    //region View implementation
    override fun init(view: MovieGalleryContract.View) {
        super.init(view)
    }

    override fun resizeImageToFitBackground(aspectRatio: Double, image: Bitmap) {
        this.view.setBackgroundImage(image.apply {
            val ratio: Double = this.width.toDouble() / this.height.toDouble()

            if (ratio > aspectRatio)
                this.width = (aspectRatio * this.height).toInt()
            else
                this.height = (this.width / aspectRatio).toInt()
        })
    }
    //endregion
}
