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

data class CreditsModel(val cast: List<CastBean>? = null,
                        val crew: List<CrewBean>? = null): Parcelable {
    data class CastBean(val isAdult: Boolean = false,
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
            @JvmField val CREATOR: Parcelable.Creator<CastBean> = object: Parcelable.Creator<CastBean> {
                override fun createFromParcel(source: Parcel): CastBean = CastBean(source)
                override fun newArray(size: Int): Array<CastBean?> = arrayOfNulls(size)
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

    data class CrewBean(val isAdult: Boolean = false,
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
            @JvmField val CREATOR: Parcelable.Creator<CrewBean> = object: Parcelable.Creator<CrewBean> {
                override fun createFromParcel(source: Parcel): CrewBean = CrewBean(source)
                override fun newArray(size: Int): Array<CrewBean?> = arrayOfNulls(size)
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
        @JvmField val CREATOR: Parcelable.Creator<CreditsModel> = object: Parcelable.Creator<CreditsModel> {
            override fun createFromParcel(source: Parcel): CreditsModel = CreditsModel(source)
            override fun newArray(size: Int): Array<CreditsModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(ArrayList<CastBean>().apply {
        source.readList(this, CastBean::class.java.classLoader)
    }, ArrayList<CrewBean>().apply { source.readList(this, CrewBean::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeList(cast)
        dest?.writeList(crew)
    }
    //endregion
}