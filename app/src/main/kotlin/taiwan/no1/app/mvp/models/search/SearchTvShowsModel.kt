package taiwan.no1.app.mvp.models.search

import android.os.Parcel
import android.os.Parcelable
import android.os.WorkSource
import taiwan.no1.app.data.entities.search.SearchTvShowsEntity
import java.util.*

/**
 * Created by weian on 2017/2/16.
 */
data class SearchTvShowsModel(val page: Int = 0,
                              val total_results: Int = 0,
                              val total_pages: Int = 0,
                              val results: List<ResultsTvShowsBean>? = null): Parcelable {

    data class ResultsTvShowsBean(val poster_path: String? = null,
                                  val popularity: Double = 0.toDouble(),
                                  val id: Int = 0,
                                  val backdrop_path: String? = null,
                                  val vote_average: Double = 0.toDouble(),
                                  val overview: String? = null,
                                  val first_air_date: String? = null,
                                  val original_language: String? = null,
                                  val vote_count: Int = 0,
                                  val name: String? = null,
                                  val original_name: String? = null,
                                  val origin_country: List<String>? = null,
                                  val genre_ids: List<Int>? = null): Parcelable {
        companion object{
            @JvmField val CREATOR: Parcelable.Creator<ResultsTvShowsBean> = object: Parcelable.Creator<ResultsTvShowsBean> {
                override fun createFromParcel(source: Parcel): ResultsTvShowsBean =
                        ResultsTvShowsBean(source)

                override fun newArray(size: Int): Array<out ResultsTvShowsBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.readString(),
                source.readDouble(),
                source.readInt(),
                source.readString(),
                source.readDouble(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readString(),
                ArrayList<String>().apply {
                    source.readList(this, String::class.java.classLoader)
                },
                ArrayList<Int>().apply {
                    source.readList(this, Int::class.java.classLoader)
                })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(poster_path)
            dest?.writeDouble(popularity)
            dest?.writeInt(id)
            dest?.writeString(backdrop_path)
            dest?.writeDouble(vote_average)
            dest?.writeString(overview)
            dest?.writeString(first_air_date)
            dest?.writeString(original_language)
            dest?.writeInt(vote_count)
            dest?.writeString(name)
            dest?.writeString(original_name)
            dest?.writeList(origin_country)
            dest?.writeList(genre_ids)
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<SearchTvShowsModel> = object : Parcelable.Creator<SearchTvShowsModel> {
            override fun createFromParcel(source: Parcel): SearchTvShowsModel
                    = SearchTvShowsModel(source)

            override fun newArray(size: Int): Array<out SearchTvShowsModel?>
                    = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readInt(),
            source.readInt(),
            source.readInt(),
            source.createTypedArrayList(ResultsTvShowsBean.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(page)
        dest?.writeInt(total_results)
        dest?.writeInt(total_pages)
        dest?.writeTypedList(results)
    }
}