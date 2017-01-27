package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * @author  Jieyi
 * @since   1/13/17
 */

data class TvDetailModel(val backdrop_path: String? = null,
                         val first_air_date: String? = null,
                         val homepage: String? = null,
                         val id: Int = 0,
                         val in_production: Boolean = false,
                         val last_air_date: String? = null,
                         val name: String? = null,
                         val number_of_episodes: Int = 0,
                         val number_of_seasons: Int = 0,
                         val original_language: String? = null,
                         val original_name: String? = null,
                         val overview: String? = null,
                         val popularity: Double = 0.toDouble(),
                         val poster_path: String? = null,
                         val status: String? = null,
                         val type: String? = null,
                         val vote_average: Double = 0.toDouble(),
                         val vote_count: Int = 0,
                         val videos: MovieDetailModel.VideosBean? = null,
                         val images: MovieImagesModel? = null,
                         val similar: MovieListResModel? = null,
                         val created_by: List<CreatedByBean>? = null,
                         val episode_run_time: List<Int>? = null,
                         val genres: List<GenresBean>? = null,
                         val languages: List<String>? = null,
                         val networks: List<NetworksBean>? = null,
                         val origin_country: List<String>? = null,
                         val production_companies: List<ProductionCompaniesBean>? = null,
                         val seasons: List<SeasonsBean>? = null): Parcelable {
    class CreatedByBean(val id: Int = 0,
                        val name: String? = null,
                        val profile_path: String? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<CreatedByBean> = object: Parcelable.Creator<CreatedByBean> {
                override fun createFromParcel(source: Parcel): CreatedByBean = CreatedByBean(source)
                override fun newArray(size: Int): Array<CreatedByBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readInt(), source.readString(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt(id)
            dest?.writeString(name)
            dest?.writeString(profile_path)
        }
    }

    class GenresBean(val id: Int = 0,
                     val name: String? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<GenresBean> = object: Parcelable.Creator<GenresBean> {
                override fun createFromParcel(source: Parcel): GenresBean = GenresBean(source)
                override fun newArray(size: Int): Array<GenresBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readInt(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt(id)
            dest?.writeString(name)
        }
    }

    class NetworksBean(val id: Int = 0,
                       val name: String? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<NetworksBean> = object: Parcelable.Creator<NetworksBean> {
                override fun createFromParcel(source: Parcel): NetworksBean = NetworksBean(source)
                override fun newArray(size: Int): Array<NetworksBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readInt(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt(id)
            dest?.writeString(name)
        }
    }

    class ProductionCompaniesBean(val name: String? = null,
                                  val id: Int = 0): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ProductionCompaniesBean> = object: Parcelable.Creator<ProductionCompaniesBean> {
                override fun createFromParcel(source: Parcel): ProductionCompaniesBean = ProductionCompaniesBean(
                        source)

                override fun newArray(size: Int): Array<ProductionCompaniesBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(), source.readInt())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(name)
            dest?.writeInt(id)
        }
    }

    class SeasonsBean(val air_date: String? = null,
                      val episode_count: Int = 0,
                      val id: Int = 0,
                      val poster_path: String? = null,
                      val season_number: Int = 0): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<SeasonsBean> = object: Parcelable.Creator<SeasonsBean> {
                override fun createFromParcel(source: Parcel): SeasonsBean = SeasonsBean(source)
                override fun newArray(size: Int): Array<SeasonsBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                source.readInt(),
                source.readInt(),
                source.readString(),
                source.readInt())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(air_date)
            dest?.writeInt(episode_count)
            dest?.writeInt(id)
            dest?.writeString(poster_path)
            dest?.writeInt(season_number)
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TvDetailModel> = object: Parcelable.Creator<TvDetailModel> {
            override fun createFromParcel(source: Parcel): TvDetailModel = TvDetailModel(source)
            override fun newArray(size: Int): Array<TvDetailModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            1.equals(source.readInt()),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            source.readParcelable<MovieDetailModel.VideosBean>(MovieDetailModel.VideosBean::class.java.classLoader),
            source.readParcelable<MovieImagesModel>(MovieImagesModel::class.java.classLoader),
            source.readParcelable<MovieListResModel>(MovieListResModel::class.java.classLoader),
            source.createTypedArrayList(CreatedByBean.CREATOR),
            ArrayList<Int>().apply {
                source.readList(this, Int::class.java.classLoader)
            },
            source.createTypedArrayList(GenresBean.CREATOR),
            ArrayList<String>().apply {
                source.readList(this, String::class.java.classLoader)
            },
            source.createTypedArrayList(NetworksBean.CREATOR),
            ArrayList<String>().apply {
                source.readList(this, String::class.java.classLoader)
            },
            source.createTypedArrayList(ProductionCompaniesBean.CREATOR),
            source.createTypedArrayList(SeasonsBean.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(backdrop_path)
        dest?.writeString(first_air_date)
        dest?.writeString(homepage)
        dest?.writeInt(id)
        dest?.writeInt((if (in_production) 1 else 0))
        dest?.writeString(last_air_date)
        dest?.writeString(name)
        dest?.writeInt(number_of_episodes)
        dest?.writeInt(number_of_seasons)
        dest?.writeString(original_language)
        dest?.writeString(original_name)
        dest?.writeString(overview)
        dest?.writeDouble(popularity)
        dest?.writeString(poster_path)
        dest?.writeString(status)
        dest?.writeString(type)
        dest?.writeDouble(vote_average)
        dest?.writeInt(vote_count)
        dest?.writeParcelable(videos, 0)
        dest?.writeParcelable(images, 0)
        dest?.writeParcelable(similar, 0)
        dest?.writeTypedList(created_by)
        dest?.writeList(episode_run_time)
        dest?.writeTypedList(genres)
        dest?.writeList(languages)
        dest?.writeTypedList(networks)
        dest?.writeList(origin_country)
        dest?.writeTypedList(production_companies)
        dest?.writeTypedList(seasons)
    }
}