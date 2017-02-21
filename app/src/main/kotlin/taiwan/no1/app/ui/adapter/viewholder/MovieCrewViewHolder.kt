package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.api.config.TMDBConfig
import taiwan.no1.app.mvp.contracts.adapter.MovieCrewAdapterContract
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import taiwan.no1.app.utilies.ViewUtils
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieCrewViewHolder(view: View): BaseViewHolder(view), MovieCrewAdapterContract.View {
    @Inject
    lateinit var presenter: MovieCrewAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader
    
    val item by bindView<CardView>(R.id.item_cast)
    val ivCast by bindView<ImageView>(R.id.iv_cast)
    val tvCharacter by bindView<TextView>(R.id.tv_character)
    val tvName by bindView<TextView>(R.id.tv_name)

    override fun initView(model: Any, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)
        
        (model as FilmCastsModel.CrewBean).let {
            ViewUtils.loadBitmapToView(this.mContext,
                    TMDBConfig.BASE_IMAGE_URL + it.profile_path,
                    listener = GlideResizeTargetListener(this.ivCast, this.item))
            this.tvCharacter.text = it.job
            this.tvName.text = it.name
        }
    }

    override fun inject() {
        this.component.inject(MovieCrewViewHolder@ this)
    }

    override fun initPresenter() {
        this.presenter.init(MovieCrewViewHolder@ this)
    }
}