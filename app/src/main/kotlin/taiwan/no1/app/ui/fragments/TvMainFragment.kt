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
class TvMainFragment: ViewPagerMainCtrlFragment() {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] TvMainFragment.
         */
        fun newInstance(): TvMainFragment = TvMainFragment().apply {
            this.arguments = Bundle().apply {}
        }
    }
    //endregion

    //region Local variables
    override val fragmentList: List<Fragment> by lazy {
        ArrayList(arrayListOf(
                TvListFragment.newInstance(CloudDataStore.Tvs.ON_THE_AIR),
                TvListFragment.newInstance(CloudDataStore.Tvs.AIRING_TODAY),
                TvListFragment.newInstance(CloudDataStore.Tvs.POPULAR),
                TvListFragment.newInstance(CloudDataStore.Tvs.TOP_RATED)))
    }
    //endregion

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
    override fun inflateView(): Int = R.layout.fragment_tvs

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
    }
    //endregion

    /**
     * Get the [Fragment] which is displaying now.
     *
     * @return current display [Fragment].
     */
    override fun getCurrentDisplayFragment(): Fragment = this.fragmentList[this.vpContainer.currentItem]
}
