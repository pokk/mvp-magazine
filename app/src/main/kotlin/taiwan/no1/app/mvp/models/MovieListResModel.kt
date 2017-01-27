package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class MovieListResModel(var page: Int = 0,
                             var total_results: Int = 0,
                             var total_pages: Int = 0,
                             var movieBriefModel: List<MovieBriefModel>? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieListResModel> = object: Parcelable.Creator<MovieListResModel> {
            override fun createFromParcel(source: Parcel): MovieListResModel = MovieListResModel(source)
            override fun newArray(size: Int): Array<MovieListResModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            source.createTypedArrayList(MovieBriefModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeTypedList(movieBriefModel)
    }
    //endregion
}
