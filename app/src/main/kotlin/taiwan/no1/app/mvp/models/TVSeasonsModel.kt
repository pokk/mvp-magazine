package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by weian on 2017/1/21.
 */

data class TVSeasonsModel(val _id: String? = null,
                          val air_date: String? = null,
                          val name: String? = null,
                          val overview: String? = null,
                          val id: Int = 0,
                          val poster_path: String? = null,
                          val season_number: Int = 0,
                          val episodes: List<TVSeasonsModel.EpisodesBean>? = null): Parcelable {
    data class EpisodesBean(val air_date: String? = null,
                            val episode_number: Int = 0,
                            val name: String? = null,
                            val overview: String? = null,
                            val id: Int = 0,
                            val production_code: String? = null,
                            val season_number: Int = 0,
                            val still_path: String? = null,
                            val vote_average: Double = 0.toDouble(),
                            val vote_count: Int = 0,
                            val crew: List<MovieCastsModel.CrewBean>? = null,
                            val guest_stars: List<TVEpisodesModel.GuestStarsBean>? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<EpisodesBean> = object: Parcelable.Creator<EpisodesBean> {
                override fun createFromParcel(source: Parcel): EpisodesBean = EpisodesBean(source)
                override fun newArray(size: Int): Array<EpisodesBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readDouble(),
                source.readInt(),
                ArrayList<MovieCastsModel.CrewBean>().apply
                { source.readList(this, CreditsModel.CrewBean::class.java.classLoader) },
                ArrayList<TVEpisodesModel.GuestStarsBean>().apply
                { source.readList(this, TVEpisodesModel.GuestStarsBean::class.java.classLoader) })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(air_date)
            dest?.writeInt(episode_number)
            dest?.writeString(name)
            dest?.writeString(overview)
            dest?.writeInt(id)
            dest?.writeString(production_code)
            dest?.writeInt(season_number)
            dest?.writeString(still_path)
            dest?.writeDouble(vote_average)
            dest?.writeInt(vote_count)
            dest?.writeList(crew)
            dest?.writeList(guest_stars)
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TVSeasonsModel> = object: Parcelable.Creator<TVSeasonsModel> {
            override fun createFromParcel(source: Parcel): TVSeasonsModel = TVSeasonsModel(source)
            override fun newArray(size: Int): Array<TVSeasonsModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            ArrayList<EpisodesBean>().apply { source.readList(this, EpisodesBean::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(_id)
        dest?.writeString(air_date)
        dest?.writeString(name)
        dest?.writeString(overview)
        dest?.writeInt(id)
        dest?.writeString(poster_path)
        dest?.writeInt(season_number)
        dest?.writeList(episodes)
    }
}