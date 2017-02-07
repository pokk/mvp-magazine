package taiwan.no1.app.ui.fragments

import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.StaggeredGridLayoutManager
import android.transition.TransitionInflater
import butterknife.bindView
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieListContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.GridSpacingItemDecorator
import taiwan.no1.app.ui.customize.LoadMoreRecyclerView
import java.util.*
import javax.inject.Inject

/**
 * @author Jieyi
 * @since 12/6/16
 */

@PerFragment
class MovieListFragment: BaseFragment(), MovieListContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_CATEGORY: String = "param_movie_category"
        // The key name of the fragment restore the status parameters. 
        private const val ARG_PARAM_INSTANCE_MOVIES: String = "param_instance_movies"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieListFragment.
         */
        fun newInstance(category: CloudDataStore.Movies): MovieListFragment = MovieListFragment().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                TransitionInflater.from(App.getAppContext()).let {
                    this.sharedElementReturnTransition = it.inflateTransition(R.transition.change_image_transform)
                    this.exitTransition = it.inflateTransition(android.R.transition.fade)
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
    private var movieList: ArrayList<MovieBriefModel>? = null
    private var maxPageIndex: Int = 1
    private var pageIndex: Int = 1
    private var loading: Boolean = true

    // Get the arguments from the bundle here.
    private val argMovieCategory: CloudDataStore.Movies by lazy {
        this.arguments.getSerializable(ARG_PARAM_CATEGORY) as CloudDataStore.Movies
    }
    //endregion

    //region Fragment lifecycle
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

        outState.putParcelableArrayList(ARG_PARAM_INSTANCE_MOVIES, this.movieList)
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
        savedInstanceState?.let {
            this.movieList = savedInstanceState.getParcelableArrayList(ARG_PARAM_INSTANCE_MOVIES)
        }

        if (null != this.movieList) {
            (this.rvMovies.adapter as CommonRecyclerAdapter).models = this.movieList!!
        }
        else {
            this.rvMovies.let {
                it.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
                it.setHasFixedSize(true)
                it.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
                // Just give a empty adapter.
                it.adapter = CommonRecyclerAdapter(Collections.emptyList(), this.hashCode())
                it.setOnBottomListener(object: LoadMoreRecyclerView.OnBottomListener {
                    override fun onBottom() {
                        presenter.requestListMovies(argMovieCategory, pageIndex++)
                    }
                })
            }

            // Request the movie data.
            this.argMovieCategory.let { this.presenter.requestListMovies(it, pageIndex++) }
        }
    }
    //endregion

    //region View implementations
    override fun obtainMovieBriefList(movieList: List<MovieBriefModel>) {
        this.movieList = ArrayList(if (null == this.movieList || this.movieList!!.isEmpty())
            movieList
        else
            this.movieList!! + movieList)

        // Because the view pager will load the fragment first, if we just set the data directly, views won't
        // be showed. To avoid it, the adapter will be reset.
        this.movieList?.let { (this.rvMovies.adapter as CommonRecyclerAdapter).addItem(it) }
        // Switch on loading new movie page.
        this.loading = true
    }
    //endregion
}
