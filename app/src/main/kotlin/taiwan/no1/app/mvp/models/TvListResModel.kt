package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   2/5/17
 */

data class TvListResModel(val page: Int = 0,
                          val total_results: Int = 0,
                          val total_pages: Int = 0,
                          val results: List<TvBriefModel>? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TvListResModel> = object: Parcelable.Creator<TvListResModel> {
            override fun createFromParcel(source: Parcel): TvListResModel = TvListResModel(source)
            override fun newArray(size: Int): Array<TvListResModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            source.createTypedArrayList(TvBriefModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeTypedList(results)
    }
    //endregion
}
