package taiwan.no1.app.ui.adapter.viewholder

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.constant.Constant
import taiwan.no1.app.mvp.contracts.adapter.TvListAdapterContract
import taiwan.no1.app.mvp.models.tv.TvBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.fragments.TvDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import taiwan.no1.app.utilies.ViewUtils
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class TvListViewHolder(val view: View): BaseViewHolder(view), TvListAdapterContract.View {
    @Inject
    lateinit var presenter: TvListAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader
    
    private val item by bindView<RelativeLayout>(R.id.item_tv_brief)
    private val ivPoster by bindView<ImageView>(R.id.iv_tv_poster)
    private val ivBackdrop by bindView<ImageView>(R.id.iv_tv_backdrop)
    private val ivBackdropFog by bindView<ImageView>(R.id.iv_fog)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val tvRelease by bindView<TextView>(R.id.tv_release_date)
    private val tvGenres by bindView<TextView>(R.id.tv_genres)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.tvGenres.text = ""
        // Cast the model data type to MovieBriefModel.
        (model as TvBriefModel).let {
            val maxLengthOfGenres: Int = it.genre_ids?.let {
                if (it.size >= 3) 3 else it.size
            } ?: 0

            ViewUtils.loadBitmapToView(this.mContext,
                    TMDBConfig.BASE_IMAGE_URL + it.poster_path,
                    this.ivPoster, isFitCenter = false)
            ViewUtils.loadBitmapToView(this.mContext,
                    TMDBConfig.BASE_IMAGE_URL + it.backdrop_path,
                    listener = object: BitmapImageViewTarget(this.ivBackdrop) {
                        // After finished loading the pic from remote.
                        override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                            super.onResourceReady(resource, glideAnimation)
                            // Extract the color from pic.
                            Palette.from(resource).generate().getVibrantColor(Color.argb(99, 79, 79, 79)).let {
                                // Set the fog in front of the backdrop.
                                ivBackdropFog.setBackgroundColor(Color.argb(33,
                                        Color.red(it),
                                        Color.green(it),
                                        Color.blue(it)))
                            }
                        }
                    }, isFitCenter = false)
            // Concat the genres category.
            var genresString: String = ""
            for (i in 0..maxLengthOfGenres - 1) {
                Constant.Genres.values().forEach { genresIndex ->
                    if (it.genre_ids!![i] == genresIndex.id)
                        genresString += "${genresIndex.categoryName} | "
                }
            }
            this.tvGenres.text = genresString.dropLast(3)
            this.tvTitle.text = it.name
            this.tvRelease.text = it.first_air_date
            this.item.setOnClickListener {
                // TODO: 2/5/17 Make the tv detail.
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(NAVIGATOR_ARG_FRAGMENT, TvDetailFragment.newInstance(model.id.toString())),
                        Pair(NAVIGATOR_ARG_TAG, adapter.fragmentTag)))
            }
        }
    }

    override fun inject() {
        this.component.inject(TvListViewHolder@ this)
    }

    override fun initPresenter() {
        this.presenter.init(TvListViewHolder@ this)
    }
}
