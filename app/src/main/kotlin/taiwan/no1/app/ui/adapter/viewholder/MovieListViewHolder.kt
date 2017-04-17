package taiwan.no1.app.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import com.devrapid.kotlinknifer.AppLog
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.mvp.contracts.adapter.MovieListAdapterContract
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * A [BaseViewHolder] of displaying brief movie introduction view of the MVP architecture's V.
 *
 * @author  Jieyi
 * @since   1/7/17
 */

@PerFragment
class MovieListViewHolder(val view: View): BaseViewHolder<MovieBriefModel>(view), MovieListAdapterContract.View {
    @Inject
    lateinit var presenter: MovieListAdapterContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val item by bindView<RelativeLayout>(R.id.item_movie_brief)
    private val ivPoster by bindView<ImageView>(R.id.iv_movie_poster)
    private val tvRelease by bindView<TextView>(R.id.tv_release)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val tvVote by bindView<TextView>(R.id.tv_vote)
    private val tvContent by bindView<TextView>(R.id.tv_brief_content)
    //endregion

    //region BaseViewHolder
    override fun initView(model: MovieBriefModel, position: Int, adapter: CommonRecyclerAdapter) {
        super.initView(model, position, adapter)

        this.ivPoster.transitionName = "poster_transition" + position
        this.tvTitle.transitionName = "title_transition" + position
        this.tvRelease.transitionName = "release_transition" + position
        this.item.setOnClickListener { this.presenter.onItemClicked(adapter.fragmentTag) }
    }

    override fun inject() {
        this.component.inject(MovieListViewHolder@ this)
    }

    override fun initPresenter(model: MovieBriefModel) {
        this.presenter.init(MovieListViewHolder@ this, model)
    }
    //endregion

    //region ViewHolder implementations
    override fun showMoviePoster(uri: String) {
        AppLog.i(uri)
        this.imageLoader.display(uri, this.ivPoster, isFitCenter = false)
    }

    override fun showMovieReleaseDate(date: String) {
        this.tvRelease.text = date
    }

    override fun showMovieTitle(title: String) {
        this.tvTitle.text = title
    }

    override fun showMovieVote(voteRate: String) {
        this.tvVote.text = voteRate
    }

    override fun showMovieOverview(overview: String) {
        this.tvContent.text = overview
    }
    //endregion
}