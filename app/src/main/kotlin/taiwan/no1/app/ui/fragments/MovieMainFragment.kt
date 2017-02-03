package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.SCROLL_STATE_IDLE
import android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING
import butterknife.bindView
import com.gigamole.navigationtabstrip.NavigationTabStrip
import com.jakewharton.rxbinding.support.v4.view.pageScrollStateChanges
import com.jakewharton.rxbinding.support.v4.view.pageSelections
import taiwan.no1.app.R
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MovieMainContract
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.MovieViewPager
import java.util.*
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   2017/01/12
 */
@PerFragment
class MovieMainFragment: BaseFragment(), MovieMainContract.View, IMainFragment {
    //region Static initialization
    companion object Factory {
        // For navigating the fragment's arguments. 
        const val NAVIGATOR_ARG_FRAGMENT = "fragment"
        const val NAVIGATOR_ARG_TAG = "tag"
        const val NAVIGATOR_ARG_SHARED_ELEMENTS = "shared_element_list"
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

    @Inject
    lateinit var presenter: MovieMainContract.Presenter

    //region View variables
    private val vpContainer by bindView<ViewPager>(R.id.vp_container)
    private val ntsTabMenu by bindView<NavigationTabStrip>(R.id.nts_center)
    //endregion

    private var prevItemPos: Int = -1
    private var currItemPos: Int = -1
    private val fragmentList: List<Fragment> by lazy {
        ArrayList(arrayListOf(
                MovieListFragment.newInstance(DataRepository.Movies.POPULAR),
                MovieListFragment.newInstance(DataRepository.Movies.NOW_PLAYING),
                MovieListFragment.newInstance(DataRepository.Movies.TOP_RATED),
                MovieListFragment.newInstance(DataRepository.Movies.UP_COMING)))
    }

    // Get the arguments from the bundle here.

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
        this.getComponent(FragmentComponent::class.java, null).inject(MovieMainFragment@ this)
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
        this.presenter.init(MovieMainFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        this.ntsTabMenu.setViewPager(this.vpContainer.apply {
            var flagClearPrevFragment: Boolean = false

            this.adapter = MovieViewPager(context(), fragmentManager, fragmentList)
            // Initial the position.
            currItemPos = this.currentItem
            prevItemPos = this.currentItem
            // View pager's listener.
            this.pageSelections().compose(bindToLifecycle<Int>()).subscribe {
                currItemPos = it
                // After change the page, the flag will be opened for clearing the previous stack fragments.
                flagClearPrevFragment = true
            }
            this.pageScrollStateChanges().compose(bindToLifecycle<Int>()).subscribe {
                // This is a trigger of changing views.
                if (SCROLL_STATE_SETTLING == it)
                    flagClearPrevFragment = false
                // Finished the view changed completely, the previous stack fragments will be cleared.
                else if (SCROLL_STATE_IDLE == it && flagClearPrevFragment) {
                    clearAllChildrenFragment(prevItemPos)
                    prevItemPos = currItemPos
                }
            }
        }, 0)
    }
    //endregion

    /**
     * Get the [Fragment] which is displaying now.
     *
     * @return current display [Fragment].
     */
    override fun getCurrentDisplayFragment(): Fragment = this.fragmentList[this.vpContainer.currentItem]

    /**
     * Clear all of the child fragments.
     *
     * @param index index of the array fragment.
     */
    private fun clearAllChildrenFragment(index: Int) {
        (0..this.fragmentList[index].childFragmentManager.backStackEntryCount - 1).forEach {
            this.fragmentList[index].childFragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
