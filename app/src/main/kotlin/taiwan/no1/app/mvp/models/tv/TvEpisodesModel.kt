package taiwan.no1.app.mvp.models.tv

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.mvp.models.CommonModel
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.ImageProfileModel

/**
 *
 * @author  weian
 * @since   1/21/17
 */

data class TvEpisodesModel(val air_date: String? = null,
                           val episode_number: Int = 0,
                           val name: String? = null,
                           val overview: String? = null,
                           val id: Int = 0,
                           val production_code: String? = null,
                           val season_number: Int = 0,
                           val still_path: String? = null,
                           val vote_count: Double = 0.toDouble(),
                           val images: ImageBean? = null,
                           val videos: CommonModel.VideosBean? = null,
                           val credits: FilmCastsModel? = null,
                           val crew: List<FilmCastsModel.CrewBean>? = null,
                           val guest_starts: List<FilmCastsModel.CastBean>? = null): Parcelable {
    //region Parcelable
    data class ImageBean(val stills: List<ImageProfileModel>? = null): Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ImageBean> = object: Parcelable.Creator<ImageBean> {
                override fun createFromParcel(source: Parcel): ImageBean = ImageBean(source)
                override fun newArray(size: Int): Array<ImageBean?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel): this(source.createTypedArrayList(ImageProfileModel.CREATOR))

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeTypedList(stills)
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TvEpisodesModel> = object: Parcelable.Creator<TvEpisodesModel> {
            override fun createFromParcel(source: Parcel): TvEpisodesModel = TvEpisodesModel(source)
            override fun newArray(size: Int): Array<TvEpisodesModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readDouble(),
            source.readParcelable<ImageBean?>(ImageBean::class.java.classLoader),
            source.readParcelable<CommonModel.VideosBean?>(CommonModel.VideosBean::class.java.classLoader),
            source.readParcelable<FilmCastsModel?>(FilmCastsModel::class.java.classLoader),
            source.createTypedArrayList(FilmCastsModel.CrewBean.CREATOR),
            source.createTypedArrayList(FilmCastsModel.CastBean.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(air_date)
        dest?.writeInt(episode_number)
        dest?.writeString(name)
        dest?.writeString(overview)
        dest?.writeInt(id)
        dest?.writeString(production_code)
        dest?.writeInt(season_number)
        dest?.writeString(still_path)
        dest?.writeDouble(vote_count)
        dest?.writeParcelable(images, 0)
        dest?.writeParcelable(videos, 0)
        dest?.writeParcelable(credits, 0)
        dest?.writeTypedList(crew)
        dest?.writeTypedList(guest_starts)
    }
    //endregion
}