package taiwan.no1.app.mvp.models.search

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by weian on 2017/2/16.
 */
data class SearchPersonModel(val page: Int = 0,
                             val total_results: Int = 0,
                             val total_pages: Int = 0,
                             val results: List<ResultsPersonBean>): Parcelable {

    data class ResultsPersonBean(val profile_path: String? = null,
                                 val adult: Boolean = false,
                                 val id: Int = 0,
                                 val name: String? = null,
                                 val popularity: Double = 0.toDouble(),
                                 val known_for: List<KnownForBean>? = null): Parcelable {

        data class KnownForBean(val poster_path: String? = null,
                                val adult: Boolean = false,
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
                                val video: Boolean = false,
                                val vote_average: Double = 0.toDouble(),
                                val genre_ids: List<Int>? = null): Parcelable {

            companion object {
                @JvmField val CREATOR: Parcelable.Creator<KnownForBean> = object : Parcelable.Creator<KnownForBean> {
                    override fun createFromParcel(source: Parcel): KnownForBean = KnownForBean(source)
                    override fun newArray(size: Int): Array<out KnownForBean?> = arrayOfNulls(size)
                }
            }

            constructor(source: Parcel): this(source.readString(),
                    1.equals(source.readInt()),
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
                    1.equals(source.readInt()),
                    source.readDouble(),
                    ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) })

            override fun describeContents() = 0

            override fun writeToParcel(dest: Parcel?, flags: Int) {
                dest?.writeString(poster_path)
                dest?.writeInt((if (adult) 1 else 0))
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
                dest?.writeInt((if (video) 1 else 0))
                dest?.writeDouble(vote_average)
                dest?.writeList(genre_ids)
            }
        }

        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ResultsPersonBean> = object : Parcelable.Creator<ResultsPersonBean> {
                override fun createFromParcel(source: Parcel): ResultsPersonBean = ResultsPersonBean(source)

                override fun newArray(size: Int): Array<out ResultsPersonBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                1.equals(source.readInt()),
                source.readInt(),
                source.readString(),
                source.readDouble(),
                ArrayList<KnownForBean>().apply {
                    source.readList(this, KnownForBean::class.java.classLoader)
                })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(profile_path)
            dest?.writeInt((if (adult) 1 else 0))
            dest?.writeInt(id)
            dest?.writeString(name)
            dest?.writeDouble(popularity)
            dest?.writeList(known_for)
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<SearchPersonModel> = object : Parcelable.Creator<SearchPersonModel> {
            override fun createFromParcel(source: Parcel): SearchPersonModel = SearchPersonModel(source)

            override fun newArray(size: Int): Array<out SearchPersonModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            ArrayList<ResultsPersonBean>().apply {
                source.readList(this, SearchPersonModel::class.java.classLoader)
            })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeTypedList(results)
    }
}