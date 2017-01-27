package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

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
                            val videos: VideosBean? = null,
                            val images: MovieImagesModel? = null,
                            val similar: MovieListResModel? = null,
                            val casts: MovieCastsModel? = null,
                            val genres: List<GenresBean>? = null,
                            val production_companies: List<ProductionCompaniesBean>? = null,
                            val production_countries: List<ProductionCountriesBean>? = null,
                            val spoken_languages: List<SpokenLanguagesBean>? = null): Parcelable {
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

    data class GenresBean(val id: Int = 0,
                          val name: String? = null): Parcelable {
        //region Parcelable
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
        //endregion
    }

    data class ProductionCompaniesBean(val name: String? = null,
                                       val id: Int = 0): Parcelable {
        //region Parcelable
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
        //endregion
    }

    data class ProductionCountriesBean(val iso_3166_1: String? = null,
                                       val name: String? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ProductionCountriesBean> = object: Parcelable.Creator<ProductionCountriesBean> {
                override fun createFromParcel(source: Parcel): ProductionCountriesBean = ProductionCountriesBean(
                        source)

                override fun newArray(size: Int): Array<ProductionCountriesBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(iso_3166_1)
            dest?.writeString(name)
        }
        //endregion
    }

    data class SpokenLanguagesBean(val iso_639_1: String? = null,
                                   val name: String? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<SpokenLanguagesBean> = object: Parcelable.Creator<SpokenLanguagesBean> {
                override fun createFromParcel(source: Parcel): SpokenLanguagesBean = SpokenLanguagesBean(
                        source)

                override fun newArray(size: Int): Array<SpokenLanguagesBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(iso_639_1)
            dest?.writeString(name)
        }
        //endregion
    }

    data class VideosBean(val results: List<MovieVideosModel>? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<VideosBean> = object: Parcelable.Creator<VideosBean> {
                override fun createFromParcel(source: Parcel): VideosBean = VideosBean(source)
                override fun newArray(size: Int): Array<VideosBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(ArrayList<MovieVideosModel>().apply {
            source.readList(this, MovieVideosModel::class.java.classLoader)
        })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeList(results)
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
            source.readParcelable<VideosBean?>(VideosBean::class.java.classLoader),
            source.readParcelable<MovieImagesModel?>(MovieImagesModel::class.java.classLoader),
            source.readParcelable<MovieListResModel?>(MovieListResModel::class.java.classLoader),
            source.readParcelable<MovieCastsModel?>(MovieCastsModel::class.java.classLoader),
            source.createTypedArrayList(GenresBean.CREATOR),
            source.createTypedArrayList(ProductionCompaniesBean.CREATOR),
            source.createTypedArrayList(ProductionCountriesBean.CREATOR),
            source.createTypedArrayList(SpokenLanguagesBean.CREATOR))

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