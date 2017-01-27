package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*


/**
 * Created by weian on 2017/1/21.
 */
data class TVEpisodesModel(val air_date: String? = null,
                           val episode_number: Int = 0,
                           val name: String? = null,
                           val overview: String? = null,
                           val id: Int = 0,
                           val production_code: String? = null,
                           val season_number: Int = 0,
                           val still_path: String? = null,
                           val vote_count: Double = 0.toDouble(),
                           val videos: MovieDetailModel.VideosBean? = null): Parcelable {
    data class ImageBean(val stills: List<StillsBean>? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ImageBean> = object: Parcelable.Creator<ImageBean> {
                override fun createFromParcel(source: Parcel): ImageBean = ImageBean(source)
                override fun newArray(size: Int): Array<ImageBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(ArrayList<StillsBean>().apply {
            source.readList(this, StillsBean::class.java.classLoader)
        })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeList(stills)
        }
    }

    data class StillsBean(val aspect_ratio: Double = 0.toDouble(),
                          val file_path: String? = null,
                          val height: Int = 0,
                          val iso_639_1: String? = null,
                          val vote_average: Double = 0.toDouble(),
                          val vote_count: Int = 0,
                          val width: Int = 0): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<StillsBean> = object: Parcelable.Creator<StillsBean> {
                override fun createFromParcel(source: Parcel): StillsBean = StillsBean(source)
                override fun newArray(size: Int): Array<StillsBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readDouble(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readDouble(),
                source.readInt(),
                source.readInt())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeDouble(aspect_ratio)
            dest?.writeString(file_path)
            dest?.writeInt(height)
            dest?.writeString(iso_639_1)
            dest?.writeDouble(vote_average)
            dest?.writeInt(vote_count)
            dest?.writeInt(width)
        }
    }

    data class GuestStarsBean(val id: Int = 0,
                              val name: String? = null,
                              val credit_id: String? = null,
                              val character: String? = null,
                              val order: Int = 0,
                              val profile_path: String? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<GuestStarsBean> = object: Parcelable.Creator<GuestStarsBean> {
                override fun createFromParcel(source: Parcel): GuestStarsBean = GuestStarsBean(source)
                override fun newArray(size: Int): Array<GuestStarsBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readInt(),
                source.readString(), source.readString(), source.readString(), source.readInt(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt(id)
            dest?.writeString(name)
            dest?.writeString(credit_id)
            dest?.writeString(character)
            dest?.writeInt(order)
            dest?.writeString(profile_path)
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TVEpisodesModel> = object: Parcelable.Creator<TVEpisodesModel> {
            override fun createFromParcel(source: Parcel): TVEpisodesModel = TVEpisodesModel(source)
            override fun newArray(size: Int): Array<TVEpisodesModel?> = arrayOfNulls(size)
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
            source.readParcelable<MovieDetailModel.VideosBean?>(MovieDetailModel.VideosBean::class.java.classLoader))

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
        dest?.writeDouble(vote_count)
        dest?.writeParcelable(videos, 0)
    }
}