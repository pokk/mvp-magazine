package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.contracts.adapter.MovieCastAdapterContract
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.fragments.CastDetailFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import taiwan.no1.app.utilies.ViewUtils
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieCastViewHolder(view: View): BaseViewHolder<FilmCastsModel.CastBean>(view), MovieCastAdapterContract.View {
    @Inject
    lateinit var presenter: MovieCastAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    val item by bindView<CardView>(R.id.item_cast)
    val ivCast by bindView<ImageView>(R.id.iv_cast)
    val tvCharacter by bindView<TextView>(R.id.tv_character)
    val tvName by bindView<TextView>(R.id.tv_name)

    override fun initView(model: FilmCastsModel.CastBean, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        model.let {
            ViewUtils.loadBitmapToView(this.mContext,
                    TMDBConfig.BASE_IMAGE_URL + it.profile_path,
                    listener = GlideResizeTargetListener(this.ivCast, this.item))
            this.tvCharacter.text = it.character
            this.tvName.text = it.name
            this.item.setOnClickListener {
                RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                        Pair(NAVIGATOR_ARG_FRAGMENT,
                                CastDetailFragment.newInstance(model.id.toString(), adapter.fragmentTag)),
                        Pair(NAVIGATOR_ARG_TAG, adapter.fragmentTag)))
            }
        }
    }

    override fun inject() {
        this.component.inject(MovieCastViewHolder@ this)
    }

    override fun initPresenter() {
        this.presenter.init(MovieCastViewHolder@ this)
    }
}