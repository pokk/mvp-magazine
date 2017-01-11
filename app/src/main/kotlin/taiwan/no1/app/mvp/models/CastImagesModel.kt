package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.data.entities.ImageInfoEntity

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */

data class CastImagesModel(val profiles: List<ImageInfoModel>? = null): Parcelable {
    companion object {
        @JvmStatic val CREATOR: Parcelable.Creator<CastImagesModel> = object: Parcelable.Creator<CastImagesModel> {
            override fun createFromParcel(source: Parcel): CastImagesModel {
                return CastImagesModel(source)
            }

            override fun newArray(size: Int): Array<CastImagesModel?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(source: Parcel): this(
            arrayListOf<ImageInfoModel>().apply {
                source.readList(this, ImageInfoEntity::class.java.classLoader)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(this.profiles)
    }

    override fun describeContents(): Int {
        return 0
    }
}
