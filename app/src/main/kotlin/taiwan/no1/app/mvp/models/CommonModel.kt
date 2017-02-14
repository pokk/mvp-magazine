package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   2/12/17
 */

object CommonModel {
    data class BaseBean(val id: Int = 0,
                        val name: String? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<BaseBean> = object: Parcelable.Creator<BaseBean> {
                override fun createFromParcel(source: Parcel): BaseBean = BaseBean(source)
                override fun newArray(size: Int): Array<BaseBean?> = arrayOfNulls(size)
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

    data class CountriesBean(val iso_3166_1: String? = null,
                             val name: String? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<CountriesBean> = object: Parcelable.Creator<CountriesBean> {
                override fun createFromParcel(source: Parcel): CountriesBean = CountriesBean(
                        source)

                override fun newArray(size: Int): Array<CountriesBean?> = arrayOfNulls(size)
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

    data class LanguagesBean(val iso_639_1: String? = null,
                             val name: String? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<LanguagesBean> = object: Parcelable.Creator<LanguagesBean> {
                override fun createFromParcel(source: Parcel): LanguagesBean = LanguagesBean(
                        source)

                override fun newArray(size: Int): Array<LanguagesBean?> = arrayOfNulls(size)
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

    data class VideosBean(val results: List<FilmVideoModel>? = null): Parcelable {
        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<VideosBean> = object: Parcelable.Creator<VideosBean> {
                override fun createFromParcel(source: Parcel): VideosBean = VideosBean(source)
                override fun newArray(size: Int): Array<VideosBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(ArrayList<FilmVideoModel>().apply {
            source.readList(this, FilmVideoModel::class.java.classLoader)
        })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeList(results)
        }
        //endregion
    }
}