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
                         val videos: CommonBean.VideosBean? = null,
                         val images: FilmImagesModel? = null,
                         val similar: MovieListResModel? = null,
                         val credits: FilmCastsModel? = null,
                         val created_by: List<CreatedByBean>? = null,
                         val episode_run_time: List<Int>? = null,
                         val genres: List<CommonBean.BaseBean>? = null,
                         val languages: List<String>? = null,
                         val networks: List<CommonBean.BaseBean>? = null,
                         val origin_country: List<String>? = null,
                         val production_companies: List<CommonBean.BaseBean>? = null,
                         val seasons: List<TvSeasonsModel>? = null): Parcelable {
    class CreatedByBean(val id: Int = 0,
                        val name: String? = null,
                        val profile_path: String? = null): Parcelable {
        //region Parcelable
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
        //endregion
    }

    //region Parcelable
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
            1 == source.readInt(),
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
            source.readParcelable<CommonBean.VideosBean?>(CommonBean.VideosBean::class.java.classLoader),
            source.readParcelable<FilmImagesModel?>(FilmImagesModel::class.java.classLoader),
            source.readParcelable<MovieListResModel?>(MovieListResModel::class.java.classLoader),
            source.readParcelable<FilmCastsModel?>(FilmCastsModel::class.java.classLoader),
            source.createTypedArrayList(CreatedByBean.CREATOR),
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
            source.createTypedArrayList(CommonBean.BaseBean.CREATOR),
            ArrayList<String>().apply { source.readList(this, String::class.java.classLoader) },
            source.createTypedArrayList(CommonBean.BaseBean.CREATOR),
            ArrayList<String>().apply { source.readList(this, String::class.java.classLoader) },
            source.createTypedArrayList(CommonBean.BaseBean.CREATOR),
            source.createTypedArrayList(TvSeasonsModel.CREATOR))

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
        dest?.writeParcelable(credits, 0)
        dest?.writeTypedList(created_by)
        dest?.writeList(episode_run_time)
        dest?.writeTypedList(genres)
        dest?.writeList(languages)
        dest?.writeTypedList(networks)
        dest?.writeList(origin_country)
        dest?.writeTypedList(production_companies)
        dest?.writeTypedList(seasons)
    }
    //endregion
}