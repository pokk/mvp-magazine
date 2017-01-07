package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.ui.adapter.viewtype.IViewTypeFactory

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
                           val genre_ids: List<Int>? = null): IVisitable, Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieBriefModel> = object: Parcelable.Creator<MovieBriefModel> {
            override fun createFromParcel(source: Parcel): MovieBriefModel {
                return MovieBriefModel(source)
            }

            override fun newArray(size: Int): Array<MovieBriefModel?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(source: Parcel): this(
            source.readString(),
            source.readByte().toInt() != 0,
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            source.readByte().toInt() != 0,
            source.readDouble(),
            arrayListOf<Int>().apply { source.readList(this, Int::class.java.classLoader) })

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.poster_path)
        dest.writeByte(if (this.isAdult) 1.toByte() else 0.toByte())
        dest.writeString(this.overview)
        dest.writeString(this.release_date)
        dest.writeInt(this.id)
        dest.writeString(this.original_title)
        dest.writeString(this.original_language)
        dest.writeString(this.title)
        dest.writeString(this.backdrop_path)
        dest.writeDouble(this.popularity)
        dest.writeInt(this.vote_count)
        dest.writeByte(if (this.isVideo) 1.toByte() else 0.toByte())
        dest.writeDouble(this.vote_average)
        dest.writeList(this.genre_ids)
    }

    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(MovieBriefModel@ this)
}