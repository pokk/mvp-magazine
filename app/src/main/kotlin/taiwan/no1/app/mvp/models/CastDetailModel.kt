package taiwan.no1.app.mvp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * @author Jieyi
 * @since 12/29/16
 */

data class CastDetailModel(val isAdult: Boolean = false,
                           val biography: String? = null,
                           val birthday: String? = null,
                           val deathday: String? = null,
                           val gender: Int = 0,
                           val homepage: String? = null,
                           val id: Int = 0,
                           val imdb_id: String? = null,
                           val name: String? = null,
                           val place_of_birth: String? = null,
                           val popularity: Double = 0.toDouble(),
                           val profile_path: String? = null,
                           val also_known_as: List<String>? = null,
                           val images: CastImagesModel? = null,
                           val combined_credits: CreditsInFilmModel? = null): Parcelable {
    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CastDetailModel> = object: Parcelable.Creator<CastDetailModel> {
            override fun createFromParcel(source: Parcel): CastDetailModel = CastDetailModel(source)
            override fun newArray(size: Int): Array<CastDetailModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readString(),
            ArrayList<String>().apply {
                source.readList(this, String::class.java.classLoader)
            },
            source.readParcelable<CastImagesModel?>(CastImagesModel::class.java.classLoader),
            source.readParcelable<CreditsInFilmModel?>(CreditsInFilmModel::class.java.classLoader))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt((if (isAdult) 1 else 0))
        dest?.writeString(biography)
        dest?.writeString(birthday)
        dest?.writeString(deathday)
        dest?.writeInt(gender)
        dest?.writeString(homepage)
        dest?.writeInt(id)
        dest?.writeString(imdb_id)
        dest?.writeString(name)
        dest?.writeString(place_of_birth)
        dest?.writeDouble(popularity)
        dest?.writeString(profile_path)
        dest?.writeList(also_known_as)
        dest?.writeParcelable(images, 0)
        dest?.writeParcelable(combined_credits, 0)
    }
    //endregion
}