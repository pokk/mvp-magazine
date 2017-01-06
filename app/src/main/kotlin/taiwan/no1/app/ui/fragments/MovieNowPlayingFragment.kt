package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieNowPlayingContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.MovieListAdapter
import taiwan.no1.app.ui.itemdecorator.GridSpacingItemDecorator
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/5/17
 */
@PerFragment
class MovieNowPlayingFragment: BaseFragment(), MovieNowPlayingContract.View {
    companion object Factory {
        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieNowPlayingFragment.
         */
        fun newInstance(): MovieNowPlayingFragment {
            val fragment: MovieNowPlayingFragment = MovieNowPlayingFragment()
            val bundle: Bundle = Bundle()
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MovieNowPlayingContract.Presenter

    private val rvMovies by bindView<RecyclerView>(R.id.rv_movie_list)

    //region Fragment lifecycle
    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java, null).inject(MovieNowPlayingFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_movie_now_playing

    /**
     * Set the presenter initialization.
     */
    override fun initPresenter() {
        this.presenter.init(MovieNowPlayingFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     */
    override fun init() {
        this.presenter.requestNowPlayingMovies()
    }
    //endregion

    override fun obtainMovieBriefList(movieList: List<MovieBriefModel>) {
        this.rvMovies.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        this.rvMovies.adapter = MovieListAdapter(this.context, movieList)
        this.rvMovies.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
    }
}
