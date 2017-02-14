package taiwan.no1.app.mvp.models.movie

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class MovieDatesModel(var maximum: String? = null,
                           var minimum: String? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieDatesModel> = object: Parcelable.Creator<MovieDatesModel> {
            override fun createFromParcel(source: Parcel): MovieDatesModel = MovieDatesModel(source)
            override fun newArray(size: Int): Array<MovieDatesModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(maximum)
        dest?.writeString(minimum)
    }
    //endregion
}
