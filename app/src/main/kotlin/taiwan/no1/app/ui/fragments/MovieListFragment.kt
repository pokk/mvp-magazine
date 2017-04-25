package taiwan.no1.app.ui.fragments

import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionInflater
import butterknife.bindView
import com.devrapid.kotlinknifer.AppLog
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.MovieListContract
import taiwan.no1.app.mvp.models.movie.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.LazyFragmentPagerAdapter
import taiwan.no1.app.ui.customize.LoadMoreRecyclerView
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   12/6/16
 */

@PerFragment
class MovieListFragment: BaseFragment(), MovieListContract.View, LazyFragmentPagerAdapter.Laziable {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_CATEGORY: String = "param_movie_category"
        // The key name of the fragment restore the status parameters. 
        private const val ARG_PARAM_INSTANCE_MOVIES: String = "param_instance_movies"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [MovieListFragment].
         */
        fun newInstance(category: CloudDataStore.Movies): MovieListFragment = MovieListFragment().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                TransitionInflater.from(App.getAppContext()).let {
                    this.sharedElementReturnTransition = it.inflateTransition(R.transition.default_transition)
                    this.exitTransition = it.inflateTransition(android.R.transition.no_transition)
                }
            }

            this.arguments = Bundle().apply {
                this.putSerializable(ARG_PARAM_CATEGORY, category)
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: MovieListContract.Presenter

    //region View variables
    private val rvMovies by bindView<LoadMoreRecyclerView>(R.id.rv_movie_list)
    //endregion

    //region Local variables
    private var maxPageIndex: Int = 1
    private var pageIndex: Int = 1
    private var loading: Boolean = true

    // Get the arguments from the bundle here.
    private val argMovieCategory: CloudDataStore.Movies by lazy {
        this.arguments.getSerializable(ARG_PARAM_CATEGORY) as CloudDataStore.Movies
    }
    //endregion

    override fun onStart() {
        super.onStart()

        AppLog.v(argMovieCategory)
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

        // FIXME: 4/25/17 popular -> up coming -> now playing -> background 會掛，presenter沒有被init
        outState.putParcelableArrayList(ARG_PARAM_INSTANCE_MOVIES, this.presenter.getMovieList())
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
        this.getComponent(FragmentComponent::class.java).inject(MoviePopularFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_movie_list

    /**
     * Set the presenter initialization in [onCreateView].
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
        this.showLoading()

        var movieList: List<MovieBriefModel> = emptyList()
        // FIXED: 2/21/17 After this was destroyed, the data will restore from instance state.
        if (null == savedInstanceState)
            this.argMovieCategory.let { this.presenter.requestListMovies(it) }  // Request the movies data.
        else {
            movieList = savedInstanceState.getParcelableArrayList(ARG_PARAM_INSTANCE_MOVIES)
            this.presenter.restoreMovieList(movieList)
        }

        // FIXED: 2/17/17 All of the components will recreate again when this view destroy so we must re-init again.
        this.rvMovies.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.setHasFixedSize(true)
            this.adapter = CommonRecyclerAdapter(movieList, this@MovieListFragment.hashCode())
            this.setOnBottomListener {
                this@MovieListFragment.presenter.requestListMovies(argMovieCategory, ++pageIndex)
            }
        }
    }
    //endregion

    //region View implementations
    override fun showMovieBriefList(movieList: List<MovieBriefModel>) {
        // Because the view pager will load the fragment first, if we just set the data directly, views won't
        // be showed. To avoid it, the adapter will be reset.
        (this.rvMovies.adapter as CommonRecyclerAdapter).addItem(movieList)
        // Switch on loading new movie page.
        this.loading = true
    }
    //endregion
}
