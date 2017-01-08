package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.data.entities.ImageInfoEntity

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class MovieImagesModel(val backdrops: List<ImageInfoModel>? = null,
                            val posters: List<ImageInfoModel>? = null): Parcelable {
    companion object {
        @JvmStatic val CREATOR: Parcelable.Creator<MovieImagesModel> = object: Parcelable.Creator<MovieImagesModel> {
            override fun createFromParcel(source: Parcel): MovieImagesModel {
                return MovieImagesModel(source)
            }

            override fun newArray(size: Int): Array<MovieImagesModel?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(source: Parcel): this(
            arrayListOf<ImageInfoModel>().apply {
                source.readList(this, ImageInfoEntity::class.java.classLoader)
            },
            arrayListOf<ImageInfoModel>().apply {
                source.readList(this, ImageInfoEntity::class.java.classLoader)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(this.backdrops)
        dest.writeList(this.posters)
    }

    override fun describeContents(): Int {
        return 0
    }
}

