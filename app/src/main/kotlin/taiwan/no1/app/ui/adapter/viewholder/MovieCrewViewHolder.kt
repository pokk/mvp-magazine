package taiwan.no1.app.ui.adapter.viewholder

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.mvp.contracts.adapter.MovieCrewAdapterContract
import taiwan.no1.app.mvp.models.FilmCastsModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.listeners.GlideResizeTargetListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying crews of the movie view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

class MovieCrewViewHolder(view: View) : BaseViewHolder<FilmCastsModel.CrewBean>(view), MovieCrewAdapterContract.View {
    @Inject
    lateinit var presenter: MovieCrewAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    val item by bindView<CardView>(R.id.item_cast)
    val ivCast by bindView<ImageView>(R.id.iv_cast)
    val tvCharacter by bindView<TextView>(R.id.tv_character)
    val tvName by bindView<TextView>(R.id.tv_name)
    //endregion

    //region BaseViewHolder
    override fun initView(model: FilmCastsModel.CrewBean, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.ivCast.viewTreeObserver.addOnGlobalLayoutListener {
            this.ivCast.measuredWidth.let {
                this.tvCharacter.width = it
                this.tvName.width = it
            }
        }
    }

    override fun inject() {
        this.component.inject(MovieCrewViewHolder@ this)
    }

    override fun initPresenter(model: FilmCastsModel.CrewBean) {
        this.presenter.init(MovieCrewViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showCrewProfilePhoto(uri: String) {
        this.imageLoader.display(uri, listener = GlideResizeTargetListener(this.ivCast, this.item))
    }

    override fun showCrewCharacter(character: String) {
        this.tvCharacter.text = character
    }

    override fun showCrewName(name: String) {
        this.tvName.text = name
    }
    //endregion
}