package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */

data class ImageInfoModel(val aspect_ratio: Double = 0.toDouble(),
                          val file_path: String? = null,
                          val height: Int = 0,
                          val iso_639_1: String? = null,
                          val vote_average: Double = 0.toDouble(),
                          val vote_count: Int = 0,
                          val width: Int = 0): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ImageInfoModel> = object: Parcelable.Creator<ImageInfoModel> {
            override fun createFromParcel(source: Parcel): ImageInfoModel = ImageInfoModel(source)
            override fun newArray(size: Int): Array<ImageInfoModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readDouble(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            source.readInt())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDouble(aspect_ratio)
        dest.writeString(file_path)
        dest.writeInt(height)
        dest.writeString(iso_639_1)
        dest.writeDouble(vote_average)
        dest.writeInt(vote_count)
        dest.writeInt(width)
    }
    //endregion
}
