package taiwan.no1.app.mvp.models.cast

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import java.util.*

/**
 *
 * @author  jieyi
 * @since   2/13/17
 */

data class CastBriefModel(val profile_path: String? = null,
                          val isAdult: Boolean = false,
                          val id: Int = 0,
                          val name: String? = null,
                          val popularity: Double = 0.toDouble(),
                          val known_for: List<KnownForBean>? = null): IVisitable, Parcelable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(CastBriefBean@ this)

    data class KnownForBean(val poster_path: String? = null,
                            val isAdult: Boolean = false,
                            val overview: String? = null,
                            val release_date: String? = null,
                            val original_title: String? = null,
                            val id: Int = 0,
                            val media_type: String? = null,
                            val original_language: String? = null,
                            val title: String? = null,
                            val backdrop_path: String? = null,
                            val popularity: Double = 0.toDouble(),
                            val vote_count: Int = 0,
                            val isVideo: Boolean = false,
                            val vote_average: Double = 0.toDouble(),
                            val first_air_date: String? = null,
                            val name: String? = null,
                            val original_name: String? = null,
                            val genre_ids: List<Int>? = null,
                            val origin_country: List<String>? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<KnownForBean> = object: Parcelable.Creator<KnownForBean> {
                override fun createFromParcel(source: Parcel): KnownForBean = KnownForBean(source)
                override fun newArray(size: Int): Array<KnownForBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                1 == source.readInt(),
                source.readString(),
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
                source.readString(),
                source.readString(),
                source.readString(),
                ArrayList<Int>().also { source.readList(it, Int::class.java.classLoader) },
                ArrayList<String>().also { source.readList(it, String::class.java.classLoader) })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(poster_path)
            dest?.writeInt((if (isAdult) 1 else 0))
            dest?.writeString(overview)
            dest?.writeString(release_date)
            dest?.writeString(original_title)
            dest?.writeInt(id)
            dest?.writeString(media_type)
            dest?.writeString(original_language)
            dest?.writeString(title)
            dest?.writeString(backdrop_path)
            dest?.writeDouble(popularity)
            dest?.writeInt(vote_count)
            dest?.writeInt((if (isVideo) 1 else 0))
            dest?.writeDouble(vote_average)
            dest?.writeString(first_air_date)
            dest?.writeString(name)
            dest?.writeString(original_name)
            dest?.writeList(genre_ids)
            dest?.writeList(origin_country)
        }
        //endregion
    }

    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CastBriefModel> = object: Parcelable.Creator<CastBriefModel> {
            override fun createFromParcel(source: Parcel): CastBriefModel = CastBriefModel(source)
            override fun newArray(size: Int): Array<CastBriefModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            1 == source.readInt(),
            source.readInt(),
            source.readString(),
            source.readDouble(),
            source.createTypedArrayList(KnownForBean.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(profile_path)
        dest?.writeInt((if (isAdult) 1 else 0))
        dest?.writeInt(id)
        dest?.writeString(name)
        dest?.writeDouble(popularity)
        dest?.writeTypedList(known_for)
    }
    //endregion
}

