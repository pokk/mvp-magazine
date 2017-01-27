package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */

data class CastImagesModel(val profiles: List<ImageInfoModel>? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CastImagesModel> = object: Parcelable.Creator<CastImagesModel> {
            override fun createFromParcel(source: Parcel): CastImagesModel = CastImagesModel(source)
            override fun newArray(size: Int): Array<CastImagesModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.createTypedArrayList(ImageInfoModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(profiles)
    }
    //endregion
}
