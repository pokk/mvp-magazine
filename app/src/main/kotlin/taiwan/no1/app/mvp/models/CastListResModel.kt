package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory

/**
 *
 * @author  Jieyi
 * @since   2/2/17
 */

data class CastListResModel(val page: Int = 0,
                            val total_results: Int = 0,
                            val total_pages: Int = 0,
                            val results: List<CastBriefBean>? = null) {

    data class CastBriefBean(val profile_path: String? = null,
                             val isAdult: Boolean = false,
                             val id: Int = 0,
                             val name: String? = null,
                             val popularity: Double = 0.toDouble()
//                             val known_for: List<KnownForBean>? = null
    ): IVisitable, Parcelable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(CastBriefBean@ this)

        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<CastBriefBean> = object: Parcelable.Creator<CastBriefBean> {
                override fun createFromParcel(source: Parcel): CastBriefBean = CastBriefBean(source)
                override fun newArray(size: Int): Array<CastBriefBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                1 == source.readInt(),
                source.readInt(),
                source.readString(),
                source.readDouble())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(profile_path)
            dest?.writeInt((if (isAdult) 1 else 0))
            dest?.writeInt(id)
            dest?.writeString(name)
            dest?.writeDouble(popularity)
        }
        //endregion
    }

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
                            val origin_country: List<String>? = null)
}