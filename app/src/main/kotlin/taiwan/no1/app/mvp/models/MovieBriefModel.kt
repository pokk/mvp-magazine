package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import java.util.*

/**
 * A movie brief information.
 *
 * @author  Jieyi
 * @since   12/28/16
 */

data class MovieBriefModel(val poster_path: String? = null,
                           val isAdult: Boolean = false,
                           val overview: String? = null,
                           val release_date: String? = null,
                           val id: Int = 0,
                           val original_title: String? = null,
                           val original_language: String? = null,
                           val title: String? = null,
                           val backdrop_path: String? = null,
                           val popularity: Double = 0.toDouble(),
                           val vote_count: Int = 0,
                           val isVideo: Boolean = false,
                           val vote_average: Double = 0.toDouble(),
                           var isMainView: Boolean = true, // This is for difference view type.
                           val genre_ids: List<Int>? = null): IVisitable, Parcelable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(MovieBriefModel@ this,
            this.isMainView)

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieBriefModel> = object: Parcelable.Creator<MovieBriefModel> {
            override fun createFromParcel(source: Parcel): MovieBriefModel = MovieBriefModel(source)
            override fun newArray(size: Int): Array<MovieBriefModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            1 == source.readInt(),
            source.readDouble(),
            1 == source.readInt(),
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(poster_path)
        dest?.writeInt((if (isAdult) 1 else 0))
        dest?.writeString(overview)
        dest?.writeString(release_date)
        dest?.writeInt(id)
        dest?.writeString(original_title)
        dest?.writeString(original_language)
        dest?.writeString(title)
        dest?.writeString(backdrop_path)
        dest?.writeDouble(popularity)
        dest?.writeInt(vote_count)
        dest?.writeInt((if (isVideo) 1 else 0))
        dest?.writeDouble(vote_average)
        dest?.writeInt((if (isMainView) 1 else 0))
        dest?.writeList(genre_ids)
    }
}