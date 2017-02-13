package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory

/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

data class FilmVideoModel(val id: String? = null,
                          val iso_639_1: String? = null,
                          val iso_3166_1: String? = null,
                          val key: String? = null,
                          val name: String? = null,
                          val site: String? = null,
                          val size: Int = 0,
                          val type: String? = null): IVisitable, Parcelable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)

    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<FilmVideoModel> = object: Parcelable.Creator<FilmVideoModel> {
            override fun createFromParcel(source: Parcel): FilmVideoModel = FilmVideoModel(source)
            override fun newArray(size: Int): Array<FilmVideoModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(iso_639_1)
        dest?.writeString(iso_3166_1)
        dest?.writeString(key)
        dest?.writeString(name)
        dest?.writeString(site)
        dest?.writeInt(size)
        dest?.writeString(type)
    }
    //endregion
}