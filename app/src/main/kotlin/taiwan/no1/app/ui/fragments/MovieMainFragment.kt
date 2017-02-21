package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import taiwan.no1.app.R
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import java.util.*

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
@PerFragment
class MovieMainFragment: ViewPagerMainCtrlFragment() {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] MovieMainFragment.
         */
        fun newInstance(): MovieMainFragment = MovieMainFragment().apply {
            this.arguments = Bundle().apply {}
        }
    }
    //endregion

    //region Local variables
    override val fragmentList: List<Fragment> by lazy {
        ArrayList(arrayListOf(
                MovieListFragment.newInstance(CloudDataStore.Movies.POPULAR),
                MovieListFragment.newInstance(CloudDataStore.Movies.NOW_PLAYING),
                MovieListFragment.newInstance(CloudDataStore.Movies.TOP_RATED),
                MovieListFragment.newInstance(CloudDataStore.Movies.UP_COMING)))
    }
    //endregion

    // Get the arguments from the bundle here.

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_movies

    /**
     * Set the presenter initialization.
     */
    override fun initPresenter() {
    }
    //endregion

    //region IMainFragment implement
    /**
     * Get the [Fragment] which is displaying now.
     *
     * @return current display [Fragment].
     */
    override fun getCurrentDisplayFragment(): Fragment = this.fragmentList[this.vpContainer.currentItem]
    //endregion
}
