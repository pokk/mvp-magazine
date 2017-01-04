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
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.PopularMovieAdapter
import taiwan.no1.app.ui.itemdecorator.GridSpacingItemDecorator
import javax.inject.Inject


/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@PerFragment
class MoviePopularFragment: BaseFragment(), MoviePopularContract.View {
    companion object Factory {
        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(): MoviePopularFragment {
            val fragment: MoviePopularFragment = MoviePopularFragment()
            val bundle: Bundle = Bundle()
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MoviePopularContract.Presenter

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
        this.getComponent(FragmentComponent::class.java, null).inject(MoviePopularFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_movie_popular

    /**
     * Set the presenter initialization.
     */
    override fun initPresenter() {
        this.presenter.init(MoviePopularFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     */
    override fun init() {
        // TODO: 12/29/16 This request timing maybe not good?!
        this.presenter.requestPopularMovies()
    }
    //endregion

    override fun obtainMovieBriefList(movieList: List<MovieBriefModel>) {
        this.rvMovies.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        this.rvMovies.adapter = PopularMovieAdapter(this.context, movieList)
        this.rvMovies.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
    }
}
