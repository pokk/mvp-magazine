package taiwan.no1.app.ui.fragments

import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.transition.TransitionInflater
import android.widget.ImageView
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.jakewharton.rxbinding.support.v7.widget.scrollEvents
import com.touchin.constant.RxbusTag
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieListContract
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
class MovieListFragment: BaseFragment(), MovieListContract.View {
    //region Static initialization
    companion object Factory {
        // For navigating the fragment's arguments. 
        const val NAVIGATOR_ARG_FRAGMENT = "fragment"
        const val NAVIGATOR_ARG_TAG = "tag"
        const val NAVIGATOR_ARG_SHARED_ELEMENT = "shared_element"
        const val NAVIGATOR_ARG_SHARED_NAME = "shared_element_name"
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_CATEGORY: String = "param_movie_category"
        // The key name of the fragment restore the status parameters. 
        private const val ARG_PARAM_INSTANCE_MOVIES: String = "param_instance_movies"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieListFragment.
         */
        fun newInstance(category: DataRepository.Movies): MovieListFragment = MovieListFragment().apply {
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
    private val rvMovies by bindView<RecyclerView>(R.id.rv_movie_list)
    //endregion

    private var movieList: ArrayList<MovieBriefModel>? = null
    // Get the arguments from the bundle here.
    private val argMovieCategory: DataRepository.Movies by lazy {
        this.arguments.getSerializable(ARG_PARAM_CATEGORY) as DataRepository.Movies
    }
    private var maxPageIndex: Int = 1
    private var pageIndex: Int = 1
    private var loading: Boolean = true

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(MovieListFragment@ this)
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
        RxBus.get().unregister(MovieListFragment@ this)

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
            this.rvMovies.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL).apply {
                rvMovies.scrollEvents().subscribe {
                    if (0 < it.dy()) {
                        val visibleItemCount: Int = this.childCount
                        val totalItemCount: Int = this.itemCount
                        val pastVisibleItems: Int = this.findFirstVisibleItemPositions(null)[0]

                        if (loading && visibleItemCount + pastVisibleItems >= totalItemCount && 0 < totalItemCount) {
                            loading = false
                            // TODO: 2017/01/10 Limit the max page. 
                            presenter.requestListMovies(argMovieCategory, pageIndex++)
                        }
                    }
                }
            }
            this.rvMovies.setHasFixedSize(true)
            this.rvMovies.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
            // Just give a empty adapter.
            this.rvMovies.adapter = CommonRecyclerAdapter(ArrayList<MovieBriefModel>(), this.hashCode())

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

    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_CHILD_NAVIGATOR)))
    fun navigateFragment(mapArgs: HashMap<String, Any>) {
        val fragment: Fragment = mapArgs[NAVIGATOR_ARG_FRAGMENT] as Fragment
        val tag: Int = mapArgs[NAVIGATOR_ARG_TAG] as Int
        val sharedElement: ImageView? = mapArgs[NAVIGATOR_ARG_SHARED_ELEMENT] as? ImageView
        val sharedElementName: String? = mapArgs[NAVIGATOR_ARG_SHARED_NAME] as? String

        // To avoid the same fragment but different hash code's fragment add the fragment.
        if (tag == this.hashCode()) {
            AppLog.w(sharedElement, sharedElementName)
            this.childFragmentManager.beginTransaction().apply {
                replace(R.id.main_container, fragment, fragment.javaClass.name)
                addToBackStack(fragment.javaClass.name)
                if (null != sharedElement && null != sharedElementName)
                    addSharedElement(sharedElement, sharedElementName)
            }.commit()
        }
    }
}
