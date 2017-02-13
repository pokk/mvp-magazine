package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class FilmImagesModel(val backdrops: List<ImageProfileModel>? = null,
                           val posters: List<ImageProfileModel>? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<FilmImagesModel> = object: Parcelable.Creator<FilmImagesModel> {
            override fun createFromParcel(source: Parcel): FilmImagesModel = FilmImagesModel(source)
            override fun newArray(size: Int): Array<FilmImagesModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.createTypedArrayList(ImageProfileModel.CREATOR),
            source.createTypedArrayList(ImageProfileModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(backdrops)
        dest?.writeTypedList(posters)
    }
    //endregion
}

