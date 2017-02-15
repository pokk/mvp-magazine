package taiwan.no1.app.mvp.models.search

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by weian on 2017/2/15.
 */

data class SearchMovieModel(val page: Int = 0,
                            val total_results: Int = 0,
                            val total_pages: Int = 0,
                            val results: List<ResultsBean>? = null): Parcelable {
    data class ResultsBean(val poster_path: String? = null,
                           val adult: Boolean = false,
                           val overview: String? = null,
                           val release_date: String? = null,
                           val id: Int = 0,
                           val original_title: String? = null,
                           val original_language: String? = null,
                           val title: String? = null,
                           val backdrop_path: String? = null,
                           val popularity: Double = 0.toDouble(),
                           val vote_count: Int = 0,
                           val video: Boolean = false,
                           val vote_average: Double = 0.toDouble(),
                           val genre_ids: List<Int>? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ResultsBean> = object: Parcelable.Creator<ResultsBean> {
                override fun createFromParcel(source: Parcel): ResultsBean = ResultsBean(
                        source)

                override fun newArray(size: Int): Array<out ResultsBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                1 == source.readInt(),
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
                ArrayList<Int>().apply {
                    source.readList(this, Int::class.java.classLoader)
                })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(poster_path)
            dest?.writeInt((if (adult) 1 else 0))
            dest?.writeString(overview)
            dest?.writeString(release_date)
            dest?.writeInt(id)
            dest?.writeString(original_title)
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
        @JvmField val CREATOR: Parcelable.Creator<SearchMovieModel> = object : Parcelable.Creator<SearchMovieModel> {
            override fun createFromParcel(source: Parcel): SearchMovieModel = SearchMovieModel(source)

            override fun newArray(size: Int): Array<out SearchMovieModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            source.createTypedArrayList(ResultsBean.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeTypedList(results)
    }
}
