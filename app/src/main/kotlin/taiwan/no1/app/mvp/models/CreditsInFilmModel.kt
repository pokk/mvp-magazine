package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   2017/01/04
 */

data class CreditsInFilmModel(val cast: List<CastInFilmBean>? = null,
                              val crew: List<CrewInFilmBean>? = null): Parcelable {
    data class CastInFilmBean(val isAdult: Boolean = false,
                              val character: String? = null,
                              val credit_id: String? = null,
                              val id: Int = 0,
                              val original_title: String? = null,
                              val poster_path: String? = null,
                              val release_date: String? = null,
                              val title: String? = null,
                              val media_type: String? = null,
                              val episode_count: Int = 0,
                              val first_air_date: String? = null,
                              val name: String? = null,
                              val original_name: String? = null): IVisitable, Parcelable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)

        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<CastInFilmBean> = object: Parcelable.Creator<CastInFilmBean> {
                override fun createFromParcel(source: Parcel): CastInFilmBean = CastInFilmBean(source)
                override fun newArray(size: Int): Array<CastInFilmBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(1.equals(source.readInt()),
                source.readString(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt((if (isAdult) 1 else 0))
            dest?.writeString(character)
            dest?.writeString(credit_id)
            dest?.writeInt(id)
            dest?.writeString(original_title)
            dest?.writeString(poster_path)
            dest?.writeString(release_date)
            dest?.writeString(title)
            dest?.writeString(media_type)
            dest?.writeInt(episode_count)
            dest?.writeString(first_air_date)
            dest?.writeString(name)
            dest?.writeString(original_name)
        }
        //endregion
    }

    data class CrewInFilmBean(val isAdult: Boolean = false,
                              val credit_id: String? = null,
                              val department: String? = null,
                              val id: Int = 0,
                              val job: String? = null,
                              val original_title: String? = null,
                              val poster_path: String? = null,
                              val release_date: String? = null,
                              val title: String? = null,
                              val media_type: String? = null): IVisitable, Parcelable {
        override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)

        //region Parcelable
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<CrewInFilmBean> = object: Parcelable.Creator<CrewInFilmBean> {
                override fun createFromParcel(source: Parcel): CrewInFilmBean = CrewInFilmBean(source)
                override fun newArray(size: Int): Array<CrewInFilmBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(1.equals(source.readInt()),
                source.readString(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt((if (isAdult) 1 else 0))
            dest?.writeString(credit_id)
            dest?.writeString(department)
            dest?.writeInt(id)
            dest?.writeString(job)
            dest?.writeString(original_title)
            dest?.writeString(poster_path)
            dest?.writeString(release_date)
            dest?.writeString(title)
            dest?.writeString(media_type)
        }
        //endregion
    }

    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CreditsInFilmModel> = object: Parcelable.Creator<CreditsInFilmModel> {
            override fun createFromParcel(source: Parcel): CreditsInFilmModel = CreditsInFilmModel(source)
            override fun newArray(size: Int): Array<CreditsInFilmModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(ArrayList<CastInFilmBean>().apply {
        source.readList(this, CastInFilmBean::class.java.classLoader)
    }, ArrayList<CrewInFilmBean>().apply { source.readList(this, CrewInFilmBean::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeList(cast)
        dest?.writeList(crew)
    }
    //endregion
}