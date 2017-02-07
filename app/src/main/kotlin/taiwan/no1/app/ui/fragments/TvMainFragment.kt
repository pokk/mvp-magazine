package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import taiwan.no1.app.R
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.TvMainContract
import java.util.*
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
@PerFragment
class TvMainFragment: MainControlFragment(), TvMainContract.View {
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

    @Inject
    lateinit var presenter: TvMainContract.Presenter

    override val fragmentList: List<Fragment> by lazy {
        ArrayList(arrayListOf(
                TvListFragment.newInstance(CloudDataStore.Tvs.ON_THE_AIR),
                TvListFragment.newInstance(CloudDataStore.Tvs.AIRING_TODAY),
                TvListFragment.newInstance(CloudDataStore.Tvs.POPULAR),
                TvListFragment.newInstance(CloudDataStore.Tvs.TOP_RATED)))
    }

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
        this.getComponent(FragmentComponent::class.java, null).inject(TvMainFragment@ this)
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
        this.presenter.init(TvMainFragment@ this)
    }
    //endregion

    /**
     * Get the [Fragment] which is displaying now.
     *
     * @return current display [Fragment].
     */
    override fun getCurrentDisplayFragment(): Fragment = this.fragmentList[this.vpContainer.currentItem]
}
