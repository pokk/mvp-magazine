package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieListContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.GridSpacingItemDecorator
import java.util.*
import javax.inject.Inject

/**
 * @author Jieyi
 * @since 12/6/16
 */

@PerFragment
class MovieListFragment: BaseFragment(), MovieListContract.View {
    companion object Factory {
        // For navigating the fragment's arguments. 
        val NAVIGATOR_ARG_FRAGMENT = "fragment"
        val NAVIGATOR_ARG_TAG = "tag"
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_CATEGORY: String = "param_movie_category"
        // The key name of the fragment restore the status parameters. 
        private val ARG_PARAM_INSTANCE_MOVIES: String = "param_instance_movies"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(category: DataRepository.Movies): MovieListFragment {
            val fragment: MovieListFragment = MovieListFragment()
            val bundle: Bundle = Bundle()
            bundle.putSerializable(this.ARG_PARAM_CATEGORY, category)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MovieListContract.Presenter

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
        RxBus.get().register(this)
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
        RxBus.get().unregister(this)

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

            this.movieList?.let { (this.rvMovies.adapter as CommonRecyclerAdapter).models = it }
        }

        if (null == this.movieList) {
            this.rvMovies.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
            this.rvMovies.setHasFixedSize(true)
            this.rvMovies.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
            // Just give a empty adapter.
            this.rvMovies.adapter = CommonRecyclerAdapter(ArrayList<MovieBriefModel>(), this.hashCode())

            // Request the movie data.
            this.argMovieCategory?.let { this.presenter.requestListMovies(it) }
        }
    }
    //endregion

    //region View implementations
    override fun obtainMovieBriefList(movieList: List<MovieBriefModel>) {
        this.movieList = ArrayList(movieList)
        // Because the view pager will load the fragment first, if we just set the data directly, views won't
        // be showed. To avoid it, the adapter will be reset.
        this.movieList?.let { this.rvMovies.adapter = CommonRecyclerAdapter(it, this.hashCode()) }
    }
    //endregion

    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_CHILD_NAVIGATOR)))
    fun navigateFragment(mapArgs: HashMap<String, Any>) {
        val fragment: Fragment = mapArgs[NAVIGATOR_ARG_FRAGMENT] as Fragment
        val tag: Int = mapArgs[NAVIGATOR_ARG_TAG] as Int

        // To avoid the same fragment but different hash code's fragment add the fragment.
        if (tag == this.hashCode()) {
            this.childFragmentManager.beginTransaction().
                    replace(R.id.main_container, fragment, fragment.javaClass.name).
                    addToBackStack(fragment.javaClass.name).
                    commit()
        }
    }

    private fun clearChildFragments() {
        for (index in 0..this.childFragmentManager.backStackEntryCount - 1) {
            this.childFragmentManager.popBackStackImmediate()
        }
    }
}
