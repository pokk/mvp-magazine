package taiwan.no1.app.mvp.models.cast

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   2/2/17
 */

data class CastListResModel(val page: Int = 0,
                            val total_results: Int = 0,
                            val total_pages: Int = 0,
                            val results: List<CastBriefModel>? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CastListResModel> = object: Parcelable.Creator<CastListResModel> {
            override fun createFromParcel(source: Parcel): CastListResModel = CastListResModel(source)
            override fun newArray(size: Int): Array<CastListResModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            source.createTypedArrayList(CastBriefModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeTypedList(results)
    }
    //endregion
}