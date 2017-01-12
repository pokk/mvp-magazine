package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.widget.RelativeLayout
import butterknife.bindView
import com.gigamole.navigationtabstrip.NavigationTabStrip
import com.roughike.bottombar.BottomBar
import taiwan.no1.app.R
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.fragments.MovieListFragment
import taiwan.no1.app.ui.fragments.MovieMainFragment
import taiwan.no1.app.ui.fragments.TVListFragment
import taiwan.no1.app.utilies.AppLog
import java.util.*
import javax.inject.Inject

/**
 * @author Jieyi
 * @since 12/6/16
 */

@PerActivity
class MainActivity: BaseActivity(), MainContract.View, HasComponent<FragmentComponent> {
    @Inject
    lateinit var presenter: MainContract.Presenter

    //region View variables
    private val rlMainContainer by bindView<RelativeLayout>(R.id.rl_main_container)
    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    private val vpContainer by bindView<ViewPager>(R.id.vp_container)
    private val ntsTabMenu by bindView<NavigationTabStrip>(R.id.nts_center)
    //endregion

    private var prevItemPos: Int = -1
    private var currItemPos: Int = -1
    private val fragmentList: List<Fragment> = ArrayList(arrayListOf(
            MovieListFragment.newInstance(DataRepository.Movies.POPULAR),
            MovieListFragment.newInstance(DataRepository.Movies.NOW_PLAYING),
            MovieListFragment.newInstance(DataRepository.Movies.TOP_RATED),
            MovieListFragment.newInstance(DataRepository.Movies.UP_COMING)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getComponent().inject(MainActivity@ this)
        this.presenter.init(MainActivity@ this)

        initFragment(savedInstanceState)
    }

    override fun getFragmentComponent(obj: Any?): FragmentComponent = super.provideFragmentComponent(obj)

    /**
     * Initialization of fragments.
     */
    fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one

        if (savedInstanceState == null) {
//            addFragment(R.id.fragment_container, MoviePopularFragment.newInstance(), false, null, null)
        }

        this.popAllFragment()
        this.bottombarMenu.setOnTabSelectListener {
            when (it) {
                R.id.tab_movies -> {
                    // TODO: 2017/01/12 Move those to MovieMainFragment.
//                    this.ntsTabMenu.setViewPager(this.vpContainer.apply {
//                        var flagClearPrevFragment: Boolean = false
//
//                        this.adapter = MovieViewPager(context(), supportFragmentManager, fragmentList)
//                        // Initial the position.
//                        currItemPos = this.currentItem
//                        prevItemPos = this.currentItem
//                        // View pager's listener.
//                        this.pageSelections().compose(bindToLifecycle<Int>()).subscribe {
//                            currItemPos = it
//                            // After change the page, the flag will be opened for clearing the previous stack fragments.
//                            flagClearPrevFragment = true
//                        }
//                        this.pageScrollStateChanges().compose(bindToLifecycle<Int>()).subscribe {
//                            // This is a trigger of changing views.
//                            if (SCROLL_STATE_SETTLING == it)
//                                flagClearPrevFragment = false
//                            // Finished the view changed completely, the previous stack fragments will be cleared.
//                            else if (SCROLL_STATE_IDLE == it && flagClearPrevFragment) {
//                                clearChildFragments(prevItemPos)
//                                prevItemPos = currItemPos
//                            }
//                        }
//                    }, 0)
                    this.addFragment(R.id.rl_main_container, MovieMainFragment.newInstance(), false)
                }
                R.id.tab_tv_dramas -> this.addFragment(R.id.rl_main_container,
                        TVListFragment.newInstance(""),
                        false)
                R.id.tab_people -> AppLog.d()
                else -> AppLog.i()
            }
        }
    }

    override fun onBackPressed() {
        val currentFragment: Fragment = this.fragmentList[this.vpContainer.currentItem]
        val currentChildFragmentManager: FragmentManager = currentFragment.childFragmentManager

        // Pop back from current presenter fragment.
        if (0 < currentChildFragmentManager.backStackEntryCount) {
            currentChildFragmentManager.popBackStack()
        }
        else
            super.onBackPressed()
    }

    private fun clearChildFragments(index: Int) {
        for (i in 0..this.fragmentList[index].childFragmentManager.backStackEntryCount - 1) {
            this.fragmentList[index].childFragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
