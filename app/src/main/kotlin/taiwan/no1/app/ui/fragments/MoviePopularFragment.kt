package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MoviePopularContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.GridSpacingItemDecorator
import taiwan.no1.app.utilies.AppLog
import java.util.*
import javax.inject.Inject

/**
 * @author Jieyi
 * @since 12/6/16
 */

@PerFragment
class MoviePopularFragment: BaseFragment(), MoviePopularContract.View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_CATEGORY: String = "param_movie_category"
        // The key name of the fragment restore the status parameters. 
        private val ARG_PARAM_INSTANCE_MOVIES: String = "param_instance_movies"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(category: DataRepository.Movies): MoviePopularFragment {
            val fragment: MoviePopularFragment = MoviePopularFragment()
            val bundle: Bundle = Bundle()
            bundle.putSerializable(this.ARG_PARAM_CATEGORY, category)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MoviePopularContract.Presenter

    //region View variables
    private val rvMovies by bindView<RecyclerView>(R.id.rv_movie_list)
    //endregion

    private var movieList: ArrayList<MovieBriefModel>? = null
    // The fragment initialization parameters.
    private var argMovieCategory: DataRepository.Movies? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the arguments from the bundle here.
        this.argMovieCategory = arguments?.getSerializable(ARG_PARAM_CATEGORY) as DataRepository.Movies
    }

    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(ARG_PARAM_INSTANCE_MOVIES, movieList)
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        AppLog.v()
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
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            this.movieList = savedInstanceState.getParcelableArrayList(ARG_PARAM_INSTANCE_MOVIES)
        }

        if (null == this.movieList)
            this.argMovieCategory?.let { this.presenter.requestPopularMovies(it) }
    }
    //endregion

    //region View implementations
    override fun obtainMovieBriefList(movieList: List<MovieBriefModel>) {
        this.movieList = ArrayList(movieList)
        
        this.rvMovies.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        this.rvMovies.adapter = CommonRecyclerAdapter(movieList)
        this.rvMovies.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
    }
    //endregion
}
