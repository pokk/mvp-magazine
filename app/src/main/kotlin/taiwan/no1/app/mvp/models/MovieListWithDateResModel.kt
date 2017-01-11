package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class MovieListWithDateResModel(var page: Int = 0,
                                     var total_results: Int = 0,
                                     var total_pages: Int = 0,
                                     val dates: MovieDatesModel? = null,
                                     var movieBriefModel: List<MovieBriefModel>? = null): Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieListWithDateResModel> = object: Parcelable.Creator<MovieListWithDateResModel> {
            override fun createFromParcel(source: Parcel): MovieListWithDateResModel = MovieListWithDateResModel(
                    source)

            override fun newArray(size: Int): Array<MovieListWithDateResModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readParcelable<MovieDatesModel?>(MovieDatesModel::class.java.classLoader),
            source.createTypedArrayList(MovieBriefModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeParcelable(dates, 0)
        dest?.writeTypedList(movieBriefModel)
    }
}
