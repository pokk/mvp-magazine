package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieTopRatedContract
import taiwan.no1.app.mvp.models.MovieBriefModel
import taiwan.no1.app.ui.BaseFragment
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/5/17
 */
@PerFragment
class MovieTopRatedFragment: BaseFragment(), MovieTopRatedContract.View {
    companion object Factory {
        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieTopRatedFragment.
         */
        fun newInstance(): MovieTopRatedFragment {
            val fragment: MovieTopRatedFragment = MovieTopRatedFragment()
            val bundle: Bundle = Bundle()
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MovieTopRatedContract.Presenter

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
        this.getComponent(FragmentComponent::class.java, null).inject(MovieTopRatedFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_movie_top_rated

    /**
     * Set the presenter initialization.
     */
    override fun initPresenter() {
        this.presenter.init(MovieTopRatedFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     */
    override fun init() {
        this.presenter.requestTopRatedMovies()
    }
    //endregion

    override fun obtainMovieBriefList(movieList: List<MovieBriefModel>) {
    }
}
