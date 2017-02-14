package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * @author  Jieyi
 * @since   12/6/16
 */

data class MovieDetailModel(val isAdult: Boolean = false,
                            val backdrop_path: String? = null,
                            val belongs_to_collection: BelongsToCollectionBean? = null,
                            val budget: Int = 0,
                            val homepage: String? = null,
                            val id: Int = 0,
                            val imdb_id: String? = null,
                            val original_language: String? = null,
                            val original_title: String? = null,
                            val overview: String? = null,
                            val popularity: Double = 0.toDouble(),
                            val poster_path: String? = null,
                            val release_date: String? = null,
                            val revenue: Int = 0,
                            val runtime: Int = 0,
                            val status: String? = null,
                            val tagline: String? = null,
                            val title: String? = null,
                            val isVideo: Boolean = false,
                            val vote_average: Double = 0.toDouble(),
                            val vote_count: Int = 0,
                            val videos: CommonModel.VideosBean? = null,
                            val images: FilmImagesModel? = null,
                            val similar: MovieListResModel? = null,
                            val casts: FilmCastsModel? = null,
                            val genres: List<CommonModel.BaseBean>? = null,
                            val production_companies: List<CommonModel.BaseBean>? = null,
                            val production_countries: List<CommonModel.CountriesBean>? = null,
                            val spoken_languages: List<CommonModel.LanguagesBean>? = null): Parcelable {
    data class BelongsToCollectionBean(val id: Int = 0,
                                       val name: String? = null,
                                       val poster_path: String? = null,
                                       val backdrop_path: String? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<BelongsToCollectionBean> = object: Parcelable.Creator<BelongsToCollectionBean> {
                override fun createFromParcel(source: Parcel): BelongsToCollectionBean = BelongsToCollectionBean(
                        source)

                override fun newArray(size: Int): Array<BelongsToCollectionBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readInt(),
                source.readString(),
                source.readString(),
                source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt(id)
            dest?.writeString(name)
            dest?.writeString(poster_path)
            dest?.writeString(backdrop_path)
        }
        //endregion
    }

    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MovieDetailModel> = object: Parcelable.Creator<MovieDetailModel> {
            override fun createFromParcel(source: Parcel): MovieDetailModel = MovieDetailModel(source)
            override fun newArray(size: Int): Array<MovieDetailModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(1.equals(source.readInt()),
            source.readString(),
            source.readParcelable<BelongsToCollectionBean?>(BelongsToCollectionBean::class.java.classLoader),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readDouble(),
            source.readInt(),
            source.readParcelable<CommonModel.VideosBean?>(CommonModel.VideosBean::class.java.classLoader),
            source.readParcelable<FilmImagesModel?>(FilmImagesModel::class.java.classLoader),
            source.readParcelable<MovieListResModel?>(MovieListResModel::class.java.classLoader),
            source.readParcelable<FilmCastsModel?>(FilmCastsModel::class.java.classLoader),
            source.createTypedArrayList(CommonModel.BaseBean.CREATOR),
            source.createTypedArrayList(CommonModel.BaseBean.CREATOR),
            source.createTypedArrayList(CommonModel.CountriesBean.CREATOR),
            source.createTypedArrayList(CommonModel.LanguagesBean.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt((if (isAdult) 1 else 0))
        dest?.writeString(backdrop_path)
        dest?.writeParcelable(belongs_to_collection, 0)
        dest?.writeInt(budget)
        dest?.writeString(homepage)
        dest?.writeInt(id)
        dest?.writeString(imdb_id)
        dest?.writeString(original_language)
        dest?.writeString(original_title)
        dest?.writeString(overview)
        dest?.writeDouble(popularity)
        dest?.writeString(poster_path)
        dest?.writeString(release_date)
        dest?.writeInt(revenue)
        dest?.writeInt(runtime)
        dest?.writeString(status)
        dest?.writeString(tagline)
        dest?.writeString(title)
        dest?.writeInt((if (isVideo) 1 else 0))
        dest?.writeDouble(vote_average)
        dest?.writeInt(vote_count)
        dest?.writeParcelable(videos, 0)
        dest?.writeParcelable(images, 0)
        dest?.writeParcelable(similar, 0)
        dest?.writeParcelable(casts, 0)
        dest?.writeTypedList(genres)
        dest?.writeTypedList(production_companies)
        dest?.writeTypedList(production_countries)
        dest?.writeTypedList(spoken_languages)
    }
    //endregion
}