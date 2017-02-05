package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   2/5/17
 */

data class TvBriefModel(val poster_path: String? = null,
                        val popularity: Double = 0.toDouble(),
                        val id: Int = 0,
                        val backdrop_path: String? = null,
                        val vote_average: Double = 0.toDouble(),
                        val overview: String? = null,
                        val first_air_date: String? = null,
                        val original_language: String? = null,
                        val vote_count: Int = 0,
                        val name: String? = null,
                        val original_name: String? = null,
                        val origin_country: List<String>? = null,
                        val genre_ids: List<Int>? = null): Parcelable, IVisitable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)

    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TvBriefModel> = object: Parcelable.Creator<TvBriefModel> {
            override fun createFromParcel(source: Parcel): TvBriefModel = TvBriefModel(source)
            override fun newArray(size: Int): Array<TvBriefModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            source.readDouble(),
            source.readInt(),
            source.readString(),
            source.readDouble(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            ArrayList<String>().apply { source.readList(this, String::class.java.classLoader) },
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(poster_path)
        dest?.writeDouble(popularity)
        dest?.writeInt(id)
        dest?.writeString(backdrop_path)
        dest?.writeDouble(vote_average)
        dest?.writeString(overview)
        dest?.writeString(first_air_date)
        dest?.writeString(original_language)
        dest?.writeInt(vote_count)
        dest?.writeString(name)
        dest?.writeString(original_name)
        dest?.writeList(origin_country)
        dest?.writeList(genre_ids)
    }
    //endregion
}
