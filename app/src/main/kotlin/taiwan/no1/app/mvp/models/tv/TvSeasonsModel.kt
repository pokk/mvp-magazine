package taiwan.no1.app.mvp.models.tv

import android.os.Parcel
import android.os.Parcelable
import taiwan.no1.app.mvp.models.CommonModel
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.mvp.models.FilmImagesModel
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.adapter.viewholder.viewtype.IViewTypeFactory

/**
 *
 * @author  weian
 * @since   1/21/17
 */

data class TvSeasonsModel(val _id: String? = null,
                          val air_date: String? = null,
                          val name: String? = null,
                          val overview: String? = null,
                          val episode_count: Int = 0,
                          val id: Int = 0,
                          val poster_path: String? = null,
                          val season_number: Int = 0,
                          val images: FilmImagesModel? = null,
                          val videos: CommonModel.VideosBean? = null,
                          val credits: FilmCastsModel? = null,
                          val episodes: List<TvEpisodesModel>? = null): IVisitable, Parcelable {
    override fun type(typeFactory: IViewTypeFactory): Int = typeFactory.type(this)

    //region Parcelable
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TvSeasonsModel> = object: Parcelable.Creator<TvSeasonsModel> {
            override fun createFromParcel(source: Parcel): TvSeasonsModel = TvSeasonsModel(source)
            override fun newArray(size: Int): Array<TvSeasonsModel?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readParcelable<FilmImagesModel?>(FilmImagesModel::class.java.classLoader),
            source.readParcelable<CommonModel.VideosBean?>(CommonModel.VideosBean::class.java.classLoader),
            source.readParcelable<FilmCastsModel?>(FilmCastsModel::class.java.classLoader),
            source.createTypedArrayList(TvEpisodesModel.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(_id)
        dest?.writeString(air_date)
        dest?.writeString(name)
        dest?.writeString(overview)
        dest?.writeInt(episode_count)
        dest?.writeInt(id)
        dest?.writeString(poster_path)
        dest?.writeInt(season_number)
        dest?.writeParcelable(images, 0)
        dest?.writeParcelable(videos, 0)
        dest?.writeParcelable(credits, 0)
        dest?.writeTypedList(episodes)
    }
    //endregion
}