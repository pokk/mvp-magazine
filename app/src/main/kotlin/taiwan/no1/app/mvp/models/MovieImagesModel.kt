package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class MovieImagesModel(val backdrops: List<ImageInfoModel>? = null,
                            val posters: List<ImageInfoModel>? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieImagesModel> = object: Parcelable.Creator<MovieImagesModel> {
            override fun createFromParcel(source: Parcel): MovieImagesModel = MovieImagesModel(source)
            override fun newArray(size: Int): Array<MovieImagesModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.createTypedArrayList(ImageInfoModel.CREATOR),
            source.createTypedArrayList(ImageInfoModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(backdrops)
        dest?.writeTypedList(posters)
    }
    //endregion
}

