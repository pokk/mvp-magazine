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
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ImageInfoModel> = object: Parcelable.Creator<ImageInfoModel> {
            override fun createFromParcel(source: Parcel): ImageInfoModel {
                return ImageInfoModel(source)
            }

            override fun newArray(size: Int): Array<ImageInfoModel?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(source: Parcel): this(source.readDouble(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            source.readInt())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDouble(this.aspect_ratio)
        dest.writeString(this.file_path)
        dest.writeInt(this.height)
        dest.writeString(this.iso_639_1)
        dest.writeDouble(this.vote_average)
        dest.writeInt(this.vote_count)
        dest.writeInt(this.width)
    }

    override fun describeContents(): Int {
        return 0
    }
}
